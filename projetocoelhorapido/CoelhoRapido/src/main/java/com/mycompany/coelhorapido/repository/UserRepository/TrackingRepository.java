
package com.mycompany.coelhorapido.repository.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackingRepository {
    
    public ArrayList<String> getOrder(String trackingCode) {
        String query = "SELECT userid, productName, price, driverid, pickupAddress, deliveryAddress, weight, notes, trackingCode FROM orders WHERE trackingCode = ?";
        ArrayList<String> orderDetails = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, trackingCode);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                orderDetails.add("User ID: " + resultSet.getInt("userid"));
                orderDetails.add("Product Name: " + getValueOrDefault(resultSet.getString("productName")));
                orderDetails.add("Price: " + resultSet.getDouble("price"));
                orderDetails.add("Driver ID: " + resultSet.getInt("driverid"));
                orderDetails.add("Pickup Address: " + getValueOrDefault(resultSet.getString("pickupAddress")));
                orderDetails.add("Delivery Address: " + getValueOrDefault(resultSet.getString("deliveryAddress")));
                orderDetails.add("Weight: " + resultSet.getDouble("weight"));
                orderDetails.add("Notes: " + getValueOrDefault(resultSet.getString("notes")));
                orderDetails.add("Tracking Code: " + getValueOrDefault(resultSet.getString("trackingCode")));
            } else {
                orderDetails.add("No order found with the given tracking code.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            orderDetails.add("Error retrieving order: " + e.getMessage());
        }
        return orderDetails;
    }

    private String getValueOrDefault(String value) {
        return value == null ? "N/A" : value;
    }
    
    
    
}
