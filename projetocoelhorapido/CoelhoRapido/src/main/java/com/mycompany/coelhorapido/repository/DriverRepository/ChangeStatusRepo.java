
package com.mycompany.coelhorapido.repository.DriverRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ChangeStatusRepo {
    
    
    public ArrayList<String> getOrdersInProgress(int driverId) {
        ArrayList<String> orders = new ArrayList<>();
        String query = "SELECT id, productName, price, trackingCode, status FROM orders WHERE status = 'Em_andamento' AND driverid = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            
            stmt.setInt(1, driverId);

            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String productName = rs.getString("productName");
                    double price = rs.getDouble("price");
                    String trackingCode = rs.getString("trackingCode");
                    String status = rs.getString("status");

                    // Formata a string para cada pedido
                    orders.add("Pedido " + id + " - Produto: " + productName +
                               " - Preço: " + price +
                               " - Código de Rastreamento: " + trackingCode +
                               " - Status: " + status);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public boolean updateOrderStatus(int orderId, String status) {
        String query = "UPDATE orders SET status = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; 

        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
}
