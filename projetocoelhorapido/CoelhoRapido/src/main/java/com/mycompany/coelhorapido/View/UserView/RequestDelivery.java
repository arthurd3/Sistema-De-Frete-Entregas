package com.mycompany.coelhorapido.View.UserView;

import com.mycompany.coelhorapido.Controller.LoginController;
import com.mycompany.coelhorapido.repository.UserRepository.RequestDeliveryRepository;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class RequestDelivery extends javax.swing.JFrame {

    private JLabel labelPickupAddress;
    private JLabel labelDeliveryAddress;
    private JLabel labelWeight;
    private JLabel labelNotes;
    private JLabel labelProductName; 
    private JTextField fieldPickupAddress;
    private JTextField fieldDeliveryAddress;
    private JTextField fieldWeight;
    private JTextField fieldProductName; 
    private JTextArea fieldNotes;
    private JButton buttonRequest;
    private JButton buttonCancel;
    private String userName;

    public RequestDelivery(String userName) {
        this.userName = userName;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        labelPickupAddress = new JLabel("Endereço de Coleta:");
        labelDeliveryAddress = new JLabel("Endereço de Entrega:");
        labelWeight = new JLabel("Peso (kg):");
        labelNotes = new JLabel("Observações:");
        labelProductName = new JLabel("Nome do Produto:"); 

        fieldPickupAddress = new JTextField();
        fieldDeliveryAddress = new JTextField();
        fieldWeight = new JTextField();
        fieldProductName = new JTextField(); 
        fieldNotes = new JTextArea(5, 20);
        JScrollPane scrollNotes = new JScrollPane(fieldNotes);

        buttonRequest = new JButton("Solicitar Entrega");
        buttonCancel = new JButton("Cancelar");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Solicitação de Entrega");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelPickupAddress)
                                        .addComponent(labelDeliveryAddress)
                                        .addComponent(labelWeight)
                                        .addComponent(labelProductName) 
                                        .addComponent(labelNotes))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fieldPickupAddress)
                                        .addComponent(fieldDeliveryAddress)
                                        .addComponent(fieldWeight)
                                        .addComponent(fieldProductName) 
                                        .addComponent(scrollNotes, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(buttonRequest)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonCancel)))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelPickupAddress)
                                        .addComponent(fieldPickupAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelDeliveryAddress)
                                        .addComponent(fieldDeliveryAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelWeight)
                                        .addComponent(fieldWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelProductName) // Novo label
                                        .addComponent(fieldProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)) 
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelNotes)
                                        .addComponent(scrollNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonRequest)
                                        .addComponent(buttonCancel))
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        buttonRequest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onRequest(evt);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onCancel(evt);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void onRequest(ActionEvent evt) {
        String pickupAddress = fieldPickupAddress.getText().trim();
        String deliveryAddress = fieldDeliveryAddress.getText().trim();
        String weightText = fieldWeight.getText().trim();
        String productName = fieldProductName.getText().trim(); // Nome do produto
        String notes = fieldNotes.getText().trim();

        if (pickupAddress.isEmpty() || deliveryAddress.isEmpty() || weightText.isEmpty() || productName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double weight;
        try {
            weight = Double.parseDouble(weightText);
            if (weight <= 0) {
                JOptionPane.showMessageDialog(this, "Peso deve ser um valor positivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Peso deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price = weight * 10;
        int driverId = 2;
        int userId;

        try {
            LoginController login = new LoginController();
            userId = login.getId(userName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao obter o ID do usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String trackingCode = UUID.randomUUID().toString();
        RequestDeliveryRepository requestRepository = new RequestDeliveryRepository();

        boolean success = requestRepository.saveOrder(userId, productName ,price, driverId, pickupAddress, deliveryAddress, weight, notes, trackingCode);

        if (success) {
            JOptionPane.showMessageDialog(this, "Solicitação de entrega salva com sucesso!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar a solicitação de entrega.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel(ActionEvent evt) {
        this.dispose();
    }

    public static void main(String[] args) {
        String userName = "lilfil";
        java.awt.EventQueue.invokeLater(() -> new RequestDelivery(userName).setVisible(true));
    }
}
