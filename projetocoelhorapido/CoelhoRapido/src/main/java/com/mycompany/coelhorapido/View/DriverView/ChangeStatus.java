package com.mycompany.coelhorapido.View.DriverView;

import com.mycompany.coelhorapido.Controller.LoginController;
import com.mycompany.coelhorapido.repository.DriverRepository.ChangeStatusRepo;
import com.mycompany.coelhorapido.repository.DriverRepository.SelectDeliveryRepository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChangeStatus extends javax.swing.JFrame {

    private JList<String> ordersList;
    private JButton completedButton;
    private JButton canceledButton;
    private String userName;
    
    public ChangeStatus(String userName) {
        this.userName = userName;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
    }

    private void initComponents() {
        setTitle("Alterar Status do Pedido");
        setLayout(new BorderLayout());

        
        LoginController login = new  LoginController();
        int driverId = login.getIdDriver(userName);
        System.out.println(driverId);
        ChangeStatusRepo repository = new ChangeStatusRepo();
        ArrayList<String> ordersInProgress = repository.getOrdersInProgress(driverId);

        
        if (ordersInProgress.isEmpty()) {
            ordersInProgress.add("Nenhum pedido em andamento.");
        }

        
        String[] ordersArray = ordersInProgress.toArray(new String[0]);
        ordersList = new JList<>(ordersArray);
        JScrollPane scrollPane = new JScrollPane(ordersList);

        
        completedButton = new JButton("Concluído");
        completedButton.setFont(new Font("Arial", Font.BOLD, 14));
        completedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeOrderStatus("Concluído");
            }
        });

        canceledButton = new JButton("Cancelado");
        canceledButton.setFont(new Font("Arial", Font.BOLD, 14));
        canceledButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeOrderStatus("Cancelado");
            }
        });

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(completedButton);
        buttonPanel.add(canceledButton);

        
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 400);
    }

    private void changeOrderStatus(String newStatus) {
        String selectedOrder = ordersList.getSelectedValue();
        if (selectedOrder == null || selectedOrder.equals("Nenhum pedido em andamento.")) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um pedido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ChangeStatusRepo repository = new ChangeStatusRepo();
        
        SelectDeliveryRepository repositoryId = new SelectDeliveryRepository();
        
        int orderId = repositoryId.extractOrderId(selectedOrder);

        
        boolean success = repository.updateOrderStatus(orderId, newStatus);

        if (success) {
            JOptionPane.showMessageDialog(this, "Status atualizado para: " + newStatus, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o status do pedido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        String userName = "lilfil";
        java.awt.EventQueue.invokeLater(() -> new ChangeStatus(userName).setVisible(true));
    }
}
