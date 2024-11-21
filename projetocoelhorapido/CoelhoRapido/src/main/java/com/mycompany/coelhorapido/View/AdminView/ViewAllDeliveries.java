package com.mycompany.coelhorapido.View.AdminView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class ViewAllDeliveries extends javax.swing.JFrame {

    private JTable deliveriesTable;
    private JScrollPane scrollPane;

    public ViewAllDeliveries() {
        initComponents();
        loadDeliveriesData();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizar Todas as Entregas");
        setSize(800, 600);
        setLocationRelativeTo(null); 

        
        setLayout(new BorderLayout());

        
        deliveriesTable = new JTable();
        scrollPane = new JScrollPane(deliveriesTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadDeliveriesData() {
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome do Produto");
        model.addColumn("Preço");
        model.addColumn("Código de Rastreamento");
        model.addColumn("Status");
        model.addColumn("Driver");

        
        String query = "SELECT o.id, o.productName, o.price, o.trackingCode, o.status, d.name AS driverName " +
                "FROM orders o " +
                "LEFT JOIN drivers d ON o.driverid = d.driver_id";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "232345");
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id")); 
                row.add(rs.getString("productName")); 
                row.add(rs.getDouble("price")); 
                row.add(rs.getString("trackingCode")); 
                row.add(rs.getString("status")); 
                row.add(rs.getString("driverName") != null ? rs.getString("driverName") : "Sem Motorista"); 
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados das entregas: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }

        
        deliveriesTable.setModel(model);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new ViewAllDeliveries().setVisible(true));
    }
}
