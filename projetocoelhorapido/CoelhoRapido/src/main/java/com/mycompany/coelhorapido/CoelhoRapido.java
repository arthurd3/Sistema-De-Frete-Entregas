package com.mycompany.coelhorapido;
import com.mycompany.coelhorapido.View.SystemView.Login;

/**
 * @author arthu
 */

public class CoelhoRapido {

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true); 
                
            }
        });
    }
}
