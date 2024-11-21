package com.mycompany.coelhorapido.View.UserView;

import com.mycompany.coelhorapido.Model.User;
import com.mycompany.coelhorapido.repository.UserRepository.ViewOrdersRepository;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ViewMyOrders extends javax.swing.JFrame {

    private JTextArea textAreaOrders;
    private JScrollPane scrollPane;

    public ViewMyOrders() {
        initComponents();
        loadOrders();
    }

    
    private void initComponents() {
        textAreaOrders = new JTextArea(15, 40);  
        textAreaOrders.setEditable(false); // Apenas leitura
        scrollPane = new JScrollPane(textAreaOrders); 

        setTitle("Meus Pedidos");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        
        loadOrders();

        pack();
        setLocationRelativeTo(null); 
    }
    
    
    private void loadOrders() {
        ViewOrdersRepository ordersRepository = new ViewOrdersRepository();
        
        ArrayList<String> orders = ordersRepository.getOrdersFromDatabase();

        // Verifica se o cliente possui pedidos
        if (orders.isEmpty()) {
            textAreaOrders.setText("Você não tem pedidos registrados.");
        } else {
            StringBuilder ordersText = new StringBuilder("Pedidos:\n\n");
            for (String order : orders) {
                ordersText.append(order).append("\n");
            }
            textAreaOrders.setText(ordersText.toString());
        }
    }

    
}
