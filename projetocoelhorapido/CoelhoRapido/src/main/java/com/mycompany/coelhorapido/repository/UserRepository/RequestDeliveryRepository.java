package com.mycompany.coelhorapido.repository.UserRepository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestDeliveryRepository {
    
    public boolean saveOrder(int userId,String productName ,double price, int driverId, String pickupAddress, String deliveryAddress, double weight, String notes , String trackingCode) {
        String status = "Pendente";
        String query = "INSERT INTO orders (userid, productName, price, driverid, pickupAddress, deliveryAddress, weight, notes , trackingCode , status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, productName);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, driverId);
            preparedStatement.setString(5, pickupAddress);
            preparedStatement.setString(6, deliveryAddress);
            preparedStatement.setDouble(7, weight);
            preparedStatement.setString(8, notes);
            preparedStatement.setString(9, trackingCode);
            preparedStatement.setString(10, status);  
            
            
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);  // Verifique o número de linhas afetadas
            return rowsAffected > 0;

        } catch (SQLException e) {
  
            System.out.println("Erro ao inserir dados: " + e.getMessage());  
            e.printStackTrace();
            return false;
        }
    }

}
   
    
   

