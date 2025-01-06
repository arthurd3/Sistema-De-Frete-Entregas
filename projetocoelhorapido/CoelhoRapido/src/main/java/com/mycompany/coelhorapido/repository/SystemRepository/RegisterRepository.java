package com.mycompany.coelhorapido.repository.SystemRepository;

import com.mycompany.coelhorapido.DatabaseConnection;
import javax.swing.JOptionPane;
import java.sql.*;

public class RegisterRepository {   

    private static boolean isNameTaken(Connection conn, String nome) throws SQLException {
        if (conn == null || conn.isClosed()) {
            JOptionPane.showMessageDialog(null, "Erro: Conexão com o banco de dados está fechada.");
            return false;
        }

        String[] tables = {"users", "drivers"};
        for (String table : tables) {
            String query = "SELECT COUNT(*) FROM " + table + " WHERE name = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, nome);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        return true; // Nome já está em uso
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao verificar a disponibilidade do nome: " + ex.getMessage());
            }
        }
        return false; // Nome não está em uso
    }
    
    public static void CreateUser(String nome, String email, String senhaStr) {
        String userType = "client";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "");
            ) {
            if (conn != null) {
                // Verifica se o nome já está em uso
                if (isNameTaken(conn, nome)) {
                    JOptionPane.showMessageDialog(null, "O nome de usuário já está em uso. Escolha outro nome.");
                    return; 
                }

                String query = "INSERT INTO users (name, email, password , userType) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setString(1, nome);
                    pst.setString(2, email);
                    pst.setString(3, senhaStr);
                    pst.setString(4, userType);

                    int rowsAffected = pst.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao realizar o cadastro.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro: Conexão com o banco de dados não estabelecida.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar obter conexão: " + ex.getMessage());
        }
    }
    
    public static void CreateDriver(String nome, String email, String senhaStr) {
        String userType = "driver";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "");
            ) {
            if (conn != null) {
                // Verifica se o nome já está em uso
                if (isNameTaken(conn, nome)) {
                    JOptionPane.showMessageDialog(null, "O nome de usuário já está em uso. Escolha outro nome.");
                    return; 
                }

                String query = "INSERT INTO drivers (name, email, password , userType) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setString(1, nome);
                    pst.setString(2, email);
                    pst.setString(3, senhaStr);
                    pst.setString(4, userType);

                    int rowsAffected = pst.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao realizar o cadastro.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro: Conexão com o banco de dados não estabelecida.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar obter conexão: " + ex.getMessage());
        }
    }

    public boolean authenticateTable(String username, String password, Connection conn) throws SQLException {
        if (conn == null || conn.isClosed()) {
            JOptionPane.showMessageDialog(null, "Erro: Conexão com o banco de dados está fechada.");
            return false;
        }

        String[] tables = {"users", "drivers"};
        for (String table : tables) {
            if (verifyInTable(table, username, password, conn)) {
                return true;
            }
        }
        return false; 
    }

    // Método para verificar um usuário em uma tabela específica
    private boolean verifyInTable(String tableName, String username, String password, Connection conn) {
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE name = ? AND password = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Usuário e senha válidos
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; // Não encontrado
    }
}
