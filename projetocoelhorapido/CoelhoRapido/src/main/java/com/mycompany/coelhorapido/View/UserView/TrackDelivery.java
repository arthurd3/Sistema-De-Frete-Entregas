package com.mycompany.coelhorapido.View.UserView;

import com.mycompany.coelhorapido.repository.UserRepository.TrackingRepository;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TrackDelivery extends javax.swing.JFrame {

   
    private JLabel labelTrackingCode;
    private JTextField fieldTrackingCode;
    private JButton buttonTrack;
    private JButton buttonCancel;
    private JTextArea fieldStatus;

    public TrackDelivery() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    
    private void initComponents() {
        
        labelTrackingCode = new JLabel("Código de Rastreamento:");
        fieldTrackingCode = new JTextField();
        buttonTrack = new JButton("Rastrear");
        buttonCancel = new JButton("Cancelar");
        fieldStatus = new JTextArea(5, 20);
        fieldStatus.setEditable(false); 
        JScrollPane scrollStatus = new JScrollPane(fieldStatus); 

        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rastreamento de Entrega");

        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelTrackingCode)
                                        .addComponent(buttonTrack))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fieldTrackingCode)
                                        .addComponent(scrollStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                        .addComponent(buttonCancel, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelTrackingCode)
                                        .addComponent(fieldTrackingCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonTrack)
                                        .addComponent(buttonCancel))
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        
        buttonTrack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onTrack(evt);
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

    
    private void onTrack(ActionEvent evt) {
        
        String trackingCode = fieldTrackingCode.getText();

        if (trackingCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o código de rastreamento.");
            return;
        }

        TrackingRepository tracking = new TrackingRepository();
        ArrayList<String> simulatedStatus = tracking.getOrder(trackingCode);

        // Concatenar os detalhes do pedido em uma única string
        StringBuilder statusText = new StringBuilder();
        for (String detail : simulatedStatus) {
            statusText.append(detail).append("\n");
        }

        
        fieldStatus.setText(statusText.toString());
        fieldStatus.setLineWrap(true); 
        fieldStatus.setWrapStyleWord(true); 
    }

    // Ação do botão "Cancelar"
    private void onCancel(ActionEvent evt) {
        this.dispose(); // Fecha a janela
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrackDelivery().setVisible(true);
            }
        });
    }
}
