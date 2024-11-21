
package com.mycompany.coelhorapido.Controller;

import com.mycompany.coelhorapido.DatabaseConnection;
import com.mycompany.coelhorapido.repository.SystemRepository.LoginRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author arthu
 */
public class LoginController {
    
    
    public boolean authenticate(String username, String password) {
        // Estabelece uma conexão com o banco de dados
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345")) {

            // Cria o repositório de login usando a conexão
            LoginRepository loginRepo = new LoginRepository(conn);

            // Chama o método de autenticação do repositório
            boolean isAuthenticated = loginRepo.authenticateRepository(username, password);

            if (isAuthenticated) {
                System.out.println("Autenticação bem-sucedida!");
                return true;
            } else {
                System.out.println("Nome de usuário ou senha incorretos.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    
    
   public String getUserType(String username) {
        String userType = null;
        String query = "SELECT usertype FROM users WHERE name = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345")) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userType = resultSet.getString("usertype"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(userType == null)
            return "driver";
        return userType;
    }
   
    
    public String getName(String username) {
        String name = null;
        String query = "SELECT name FROM users WHERE name = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345")) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                name = resultSet.getString("name"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }
    
    
    public int getId(String username) {
        int userId = 0;
        String query = "SELECT user_id FROM users WHERE name = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345")) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("user_id"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return userId;
    }
    
    public int getIdDriver(String username) {
        int driverId = 0;
        String query = "SELECT driver_id FROM drivers WHERE name = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345")) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                driverId = resultSet.getInt("driver_id"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return driverId;
    }
}





