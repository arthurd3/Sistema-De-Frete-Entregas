package com.mycompany.coelhorapido.View.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DriverEditor extends javax.swing.JFrame {

    private int driverId;
    private JTextField jTextFieldName;
    private JTextField jTextFieldEmail;
    private JPasswordField jPasswordFieldSenha;
    private JButton jButtonSave;
    private JButton jButtonCancel;

    public DriverEditor(int driverId) {
        this.driverId = driverId;
        initComponents();
        loadUserData();  
    }

    private void initComponents() {

        
        jTextFieldName = new JTextField();
        jTextFieldEmail = new JTextField();
        jPasswordFieldSenha = new JPasswordField();
        jButtonSave = new JButton("Salvar");
        jButtonCancel = new JButton("Cancelar");

        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Driver");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(30, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldName)
                                        .addComponent(jTextFieldEmail)
                                        .addComponent(jPasswordFieldSenha)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButtonSave, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                .addGap(30)
                                                .addComponent(jButtonCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                )
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(40, Short.MAX_VALUE)
                                .addComponent(jTextFieldName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldEmail, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPasswordFieldSenha, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonSave)
                                        .addComponent(jButtonCancel))
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        // Adicionando funcionalidade aos botões
        jButtonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });

        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  
            }
        });

        pack();
        setLocationRelativeTo(null);  
    }

    
    private void loadUserData() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "")) {
            if (conn != null) {
                String query = "SELECT name, email, password FROM drivers WHERE driver_id = ?";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setInt(1, driverId);
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            jTextFieldName.setText(rs.getString("name"));
                            jTextFieldEmail.setText(rs.getString("email"));
                            jPasswordFieldSenha.setText(rs.getString("password"));
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao carregar os dados do usuário: " + ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao tentar conectar ao banco de dados: " + ex.getMessage());
        }
    }

    
    private void saveUser() {
        String name = jTextFieldName.getText().trim();
        String email = jTextFieldEmail.getText().trim();
        String password = new String(jPasswordFieldSenha.getPassword()).trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            return;
        }

        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um e-mail válido.");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coelhorapido", "root", "0")) {
            if (conn != null) {
                String query = "UPDATE drivers SET name = ?, email = ?, password = ? WHERE driver_id = ?";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setString(1, name);
                    pst.setString(2, email);
                    pst.setString(3, password);
                    pst.setInt(4, driverId);

                    int rowsAffected = pst.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!");
                        dispose(); 
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao atualizar o usuário.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar os dados do usuário: " + ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao tentar conectar ao banco de dados: " + ex.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int driverId = 1; 
                new DriverEditor(driverId).setVisible(true);
            }
        });
    }
}
