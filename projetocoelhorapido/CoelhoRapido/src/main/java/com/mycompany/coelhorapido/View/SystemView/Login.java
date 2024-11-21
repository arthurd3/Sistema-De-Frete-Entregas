
package com.mycompany.coelhorapido.View.SystemView;

import com.mycompany.coelhorapido.View.SystemView.Register;
import com.mycompany.coelhorapido.View.SystemView.MainMenu;
import com.mycompany.coelhorapido.Controller.LoginController;
import com.mycompany.coelhorapido.Model.User;
import javax.swing.JOptionPane;



public class Login extends javax.swing.JFrame {


    public Login() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLogin = new javax.swing.JLabel();
        jLabelSenha = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jPasswordInicio = new javax.swing.JPasswordField();
        jTextFieldInicio = new javax.swing.JTextField();
        jButtonLogin = new javax.swing.JButton();
        jCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Login");
        setForeground(java.awt.Color.gray);
        setName("Tela Login"); // NOI18N
        setSize(new java.awt.Dimension(314, 472));

        jLabelLogin.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabelLogin.setText("Login");
        jLabelLogin.setToolTipText("");

        jLabelSenha.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelSenha.setText("Senha");

        jLabelUsuario.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelUsuario.setText("Usuario");

        jPasswordInicio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPasswordInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordInicioActionPerformed(evt);
            }
        });

        jTextFieldInicio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextFieldInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInicioActionPerformed(evt);
            }
        });

        jButtonLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jCadastrar.setText("Cadastrar");
        jCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                            .addComponent(jTextFieldInicio)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabelLogin)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelLogin)
                .addGap(37, 37, 37)
                .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCadastrar)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordInicioActionPerformed

        char[] password = jPasswordInicio.getPassword();
        String passwordStr = new String(password);


    }//GEN-LAST:event_jPasswordInicioActionPerformed

    private void jTextFieldInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInicioActionPerformed

        String username = jTextFieldInicio.getText();

    }//GEN-LAST:event_jTextFieldInicioActionPerformed

    private void jCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCadastrarActionPerformed

        Register register = new Register();
        register.setVisible(true);

    }//GEN-LAST:event_jCadastrarActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed

        String username = jTextFieldInicio.getText();
        char[] password = jPasswordInicio.getPassword();

        User user  = new User();
        user.setName(username);

        LoginController loginController = new LoginController();
        int userId = loginController.getId(username);
        user.setId(userId);
        System.out.println(userId);

        if (username.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            return;
        }

        String passwordString = new String(password);


        boolean loginSuccess = loginController.authenticate(username, passwordString);

        if (loginSuccess) {

            String userType = loginController.getUserType(username);



            if (userType == null) {
                JOptionPane.showMessageDialog(this, "Tipo de usuário não encontrado. Tente novamente.");
                return;
            }

            System.out.println("Tipo de usuário: " + userType);

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new MainMenu(userType, username).setVisible(true);
                }
            });

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Nome de usuário ou senha inválidos. Tente novamente.");
        }
    }//GEN-LAST:event_jButtonLoginActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jCadastrar;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPasswordField jPasswordInicio;
    private javax.swing.JTextField jTextFieldInicio;
    // End of variables declaration//GEN-END:variables
}
