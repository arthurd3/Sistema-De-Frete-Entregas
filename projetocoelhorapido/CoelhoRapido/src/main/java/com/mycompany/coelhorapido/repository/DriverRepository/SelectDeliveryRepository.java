package com.mycompany.coelhorapido.repository.DriverRepository;


import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SelectDeliveryRepository {

    public ArrayList<String> getOrdersFromDatabaseDriver() {
        ArrayList<String> orders = new ArrayList<>();

        String query = "SELECT id, productName, price, trackingCode, status FROM orders WHERE status = 'pendente' "; 

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345");
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int orderId = rs.getInt("id");
                String productName = rs.getString("productName");
                double price = rs.getDouble("price");
                String trackingCode = rs.getString("trackingCode");
                String status = rs.getString("status");

                
                orders.add("Pedido " + orderId + " - Produto: " + productName + " - Preço: " + price +
                           " - Código de Rastreamento: " + trackingCode + " - Status: " + status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
    
    public int extractOrderId(String orderDetails) {
        try {
            // Supondo que o formato seja "Pedido X - ..." (captura o número X)
            String[] parts = orderDetails.split(" ");
            return Integer.parseInt(parts[1]); // Captura o número após "Pedido"
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        }
    }
    
    public void updateOrderStatusToInProgress(int orderId , int driverId) {
        
        String query = "UPDATE orders SET status = 'Em_andamento', driverid = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345"); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, driverId); 
            stmt.setInt(2, orderId);  
            stmt.executeUpdate();    

        } catch (SQLException e) {
            e.printStackTrace(); 
            JOptionPane.showMessageDialog(null, 
                "Erro ao atualizar o status do pedido: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

}
