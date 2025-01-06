package com.mycompany.coelhorapido.View.AdminView;

import com.mycompany.coelhorapido.DatabaseConnection;
import com.mycompany.coelhorapido.View.SystemView.Register;
import com.mycompany.coelhorapido.View.SystemView.RegisterDriver;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class ManageUsers extends javax.swing.JFrame {

    private JTable usersTable, driversTable;
    private JScrollPane usersScrollPane, driversScrollPane;
    private JButton addUserButton, editUserButton, deleteUserButton;
    private JButton addDriverButton, editDriverButton, deleteDriverButton;
    private JTabbedPane tabbedPane;

    public ManageUsers() {
        initComponents();
        loadUsersData();
        loadDriversData();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciar Usuários e Motoristas");
        setSize(800, 600);
        setLocationRelativeTo(null);

       
        tabbedPane = new JTabbedPane();

        
        JPanel usersPanel = new JPanel(new BorderLayout());
        usersTable = new JTable();
        usersScrollPane = new JScrollPane(usersTable);
        usersPanel.add(usersScrollPane, BorderLayout.CENTER);

        JPanel usersButtonPanel = new JPanel(new FlowLayout());
        addUserButton = new JButton("Adicionar Usuário");
        editUserButton = new JButton("Editar Usuário");
        deleteUserButton = new JButton("Excluir Usuário");
        usersButtonPanel.add(addUserButton);
        usersButtonPanel.add(editUserButton);
        usersButtonPanel.add(deleteUserButton);

        usersPanel.add(usersButtonPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Usuários", usersPanel);

        
        JPanel driversPanel = new JPanel(new BorderLayout());
        driversTable = new JTable();
        driversScrollPane = new JScrollPane(driversTable);
        driversPanel.add(driversScrollPane, BorderLayout.CENTER);

        JPanel driversButtonPanel = new JPanel(new FlowLayout());
        addDriverButton = new JButton("Adicionar Motorista");
        editDriverButton = new JButton("Editar Motorista");
        deleteDriverButton = new JButton("Excluir Motorista");
        driversButtonPanel.add(addDriverButton);
        driversButtonPanel.add(editDriverButton);
        driversButtonPanel.add(deleteDriverButton);

        driversPanel.add(driversButtonPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Motoristas", driversPanel);

        add(tabbedPane);

        
        addUserButton.addActionListener(e -> onAddUser());
        editUserButton.addActionListener(e -> onEditUser());
        deleteUserButton.addActionListener(e -> onDeleteUser());

        
        addDriverButton.addActionListener(e -> onAddDriver());
        editDriverButton.addActionListener(e -> onEditDriver());
        deleteDriverButton.addActionListener(e -> onDeleteDriver());
    }

    private void loadUsersData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Email");
        model.addColumn("Tipo de Usuário");

        String query = "SELECT user_id, name, email, userType FROM users";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "0");
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("user_id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("email"));
                row.add(rs.getString("userType"));
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados dos usuários: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }

        usersTable.setModel(model);
    }

    private void loadDriversData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Email");

        String query = "SELECT driver_id, name, email FROM drivers";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "0");
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("driver_id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("email"));
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados dos motoristas: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }

        driversTable.setModel(model);
    }
    //USER
    private void onAddUser() {
        Register register = new Register();
        register.setVisible(true);
    }

    private void onEditUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.");
            return;
        }
        int userId = (int) usersTable.getValueAt(selectedRow, 0);
        UserEditor editor = new UserEditor(userId);
        editor.setVisible(true);
    }

    private void onDeleteUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir.");
            return;
        }
        int userId = (int) usersTable.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o usuário de ID: " + userId + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "0");
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE user_id = ?")) {

                stmt.setInt(1, userId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso!");
                loadUsersData();

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao excluir usuário: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // DRIVER
    private void onAddDriver() {
        RegisterDriver register = new RegisterDriver();
        register.setVisible(true);
    }

    private void onEditDriver() {
        int selectedRow = driversTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um motorista para editar.");
            return;
        }
        int driverId = (int) driversTable.getValueAt(selectedRow, 0);
        DriverEditor editor = new DriverEditor(driverId);
        editor.setVisible(true);

    }

    private void onDeleteDriver() {
        int selectedRow = driversTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um motorista para excluir.");
            return;
        }
        int driverId = (int) driversTable.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o motorista de ID: " + driverId + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "0");
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM drivers WHERE driver_id = ?");
                stmt.setInt(1, driverId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Motorista excluído com sucesso!");
                loadDriversData();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao excluir motorista: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                        System.out.println("Conexão fechada com sucesso.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new ManageUsers().setVisible(true));
    }
}
