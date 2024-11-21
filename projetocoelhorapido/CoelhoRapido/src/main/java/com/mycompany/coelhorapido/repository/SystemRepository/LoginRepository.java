package com.mycompany.coelhorapido.repository.SystemRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author arthu
 */
public class LoginRepository {
    private Connection conn;

    
    public LoginRepository(Connection conn) {
        this.conn = conn;
    }

    public boolean authenticateRepository(String username, String password) {
        
        String[] tables = {"users", "drivers"};

        
        for (String table : tables) {
            if (authenticateInTable(table, username, password)) {
                return true;
            }
        }
        return false; 
    }

    private boolean authenticateInTable(String tableName, String username, String password) {
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE name = ? AND password = ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; 
    }
}
