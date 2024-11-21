// Remova a importação do AddDriver, pois não será mais utilizada
package com.mycompany.coelhorapido.View.SystemView;

import com.mycompany.coelhorapido.View.AdminView.ManageUsers;
import com.mycompany.coelhorapido.View.AdminView.ViewAllDeliveries;
import com.mycompany.coelhorapido.View.DriverView.ChangeStatus;
import com.mycompany.coelhorapido.View.DriverView.SelectDelivery;
import com.mycompany.coelhorapido.View.UserView.RequestDelivery;
import com.mycompany.coelhorapido.View.UserView.TrackDelivery;
import com.mycompany.coelhorapido.View.UserView.ViewMyOrders;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends javax.swing.JFrame {

    private String userType;
    private String userName;

    public MainMenu(String userType, String userName) {
        this.userType = userType;
        this.userName = userName;
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");

        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        switch (userType) {
            case "client":
                addClientButtons(gbc);
                break;
            case "driver":
                addDriverButtons(gbc);
                break;
            case "admin":
                addAdminButtons(gbc);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Tipo de usuário inválido!");
                return;
        }

        jPanel1.setBackground(new Color(240, 240, 240));
        add(jPanel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }


    private void addClientButtons(GridBagConstraints gbc) {
        addButton(gbc, "Solicitar entrega", this::onRequestDelivery, 0);
        addButton(gbc, "Acompanhar entrega", this::onTrackDelivery, 1);
        addButton(gbc, "Ver meus pedidos", this::onViewMyOrders, 2);
    }


    private void addDriverButtons(GridBagConstraints gbc) {
        addButton(gbc, "Selecionar entrega", this::onSelectDelivery, 0);
        addButton(gbc, "Alterar status", this::onChangeStatus, 1);
    }


    private void addAdminButtons(GridBagConstraints gbc) {
        addButton(gbc, "Ver todas as entregas", this::onViewAllDeliveries, 0);
        addButton(gbc, "Gerenciar usuários", this::onManageUsers, 1);
    }


    private void addButton(GridBagConstraints gbc, String text, ActionListener action, int gridY) {
        JButton button = createStyledButton(text);
        button.addActionListener(action);
        gbc.gridx = 0;
        gbc.gridy = gridY;
        jPanel1.add(button, gbc);
    }


    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(50, 150, 255));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(250, 50));
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        return button;
    }

    //CLIENT
    private void onRequestDelivery(ActionEvent evt) {
        RequestDelivery requestDelivery = new RequestDelivery(userName);
        requestDelivery.setVisible(true);
    }
    //CLIENT
    private void onTrackDelivery(ActionEvent evt) {
        TrackDelivery trackDelivery = new TrackDelivery();
        trackDelivery.setVisible(true);
    }
    //CLIENT
    private void onViewMyOrders(ActionEvent evt) {
        ViewMyOrders viewMyOrders = new ViewMyOrders();
        viewMyOrders.setVisible(true);
    }

    //DRIVER
    private void onSelectDelivery(ActionEvent evt) {
        SelectDelivery selectDelivery = new SelectDelivery(userName);
        selectDelivery.setVisible(true);
    }
    //DRIVER
    private void onChangeStatus(ActionEvent evt) {
        ChangeStatus changeStatus = new ChangeStatus(userName);
        changeStatus.setVisible(true);
    }


    //ADMIN
    private void onViewAllDeliveries(ActionEvent evt) {
        ViewAllDeliveries viewDeliveries = new ViewAllDeliveries();
        viewDeliveries.setVisible(true);
    }
    //ADMIN
    private void onManageUsers(ActionEvent evt) {
        ManageUsers manageUsers = new ManageUsers();
        manageUsers.setVisible(true);
    }

    public static void main(String args[]) {
        String userType = "client";
        String userName = "lilfil";
        java.awt.EventQueue.invokeLater(() -> new MainMenu(userType, userName).setVisible(true));
    }

    private javax.swing.JPanel jPanel1;
}
