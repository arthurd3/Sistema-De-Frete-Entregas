package com.mycompany.coelhorapido.View.DriverView;

import com.mycompany.coelhorapido.Controller.LoginController;
import com.mycompany.coelhorapido.repository.DriverRepository.SelectDeliveryRepository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectDelivery extends javax.swing.JFrame {

    private JList<String> deliveryList;
    private JButton selectButton;
    private JTextArea deliveryDetails;
    private JScrollPane listScrollPane;
    private String userName;
    
    public SelectDelivery(String userName) {
        this.userName = userName;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
    }

    private void initComponents() {
        setTitle("Seleção de Pedidos");
        setLayout(new BorderLayout());
        
        
        SelectDeliveryRepository repository = new SelectDeliveryRepository();
        ArrayList<String> ordersList = repository.getOrdersFromDatabaseDriver();

        
        if (ordersList.isEmpty()) {
            ordersList.add("Nenhum pedido disponível."); 
        }

        
        String[] ordersArray = ordersList.toArray(new String[0]);
        deliveryList = new JList<>(ordersArray);
        listScrollPane = new JScrollPane(deliveryList);
        deliveryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
        deliveryDetails = new JTextArea(10, 30); 
        deliveryDetails.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(deliveryDetails);

        
        selectButton = new JButton("Selecionar Pedido");
        selectButton.setFont(new Font("Arial", Font.BOLD, 14));
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectOrder();
            }
        });

        
        add(listScrollPane, BorderLayout.WEST);
        add(detailsScrollPane, BorderLayout.CENTER);
        add(selectButton, BorderLayout.SOUTH);

        setSize(800, 500); 
    }

    private void selectOrder() {
        String selectedOrder = deliveryList.getSelectedValue();
        if (selectedOrder == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um pedido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        
        
        SelectDeliveryRepository repository = new SelectDeliveryRepository();
        
        
        int orderId = repository.extractOrderId(selectedOrder);
        System.out.println(orderId);
        
        if (orderId == -1) {
            JOptionPane.showMessageDialog(this, "Erro ao extrair o ID do pedido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        LoginController login = new  LoginController();
        int driverId = login.getIdDriver(userName);
        
        
        repository.updateOrderStatusToInProgress(orderId , driverId);

        
        deliveryDetails.setText(selectedOrder);

        
        dispose();
    }

    public static void main(String[] args) {
        String userName = "lilfil";
        java.awt.EventQueue.invokeLater(() -> new SelectDelivery(userName).setVisible(true));
    }
}
