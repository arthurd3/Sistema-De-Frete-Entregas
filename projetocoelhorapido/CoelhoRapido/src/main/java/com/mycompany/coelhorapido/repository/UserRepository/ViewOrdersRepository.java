
package com.mycompany.coelhorapido.repository.UserRepository;

import com.mycompany.coelhorapido.Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ViewOrdersRepository {
    
    public ArrayList<String> getOrdersFromDatabase() {
        ArrayList<String> orders = new ArrayList<>();

        String query = "SELECT * FROM orders WHERE userid = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345");
             PreparedStatement pst = conn.prepareStatement(query)) {

            
            int userId = User.getId();
            System.out.println(userId);
            pst.setInt(1, userId);  

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String orderId = rs.getString("id");
                String trackingCode = rs.getString("trackingCode");
                String status = rs.getString("status");
                String productName = rs.getString("productName");
                String price = rs.getString("price");
                orders.add("Pedido " + orderId + " - Produto: " + productName + " - Preço: " + price +
                           " - Código de Rastreamento: " + trackingCode + " - Status: " + status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
    
    

}
