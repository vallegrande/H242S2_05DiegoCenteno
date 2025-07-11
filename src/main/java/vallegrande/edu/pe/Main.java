package vallegrande.edu.pe;

import vallegrande.edu.pe.view.LoginView;
import vallegrande.edu.pe.controller.LoginController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Configurar el look and feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Ejecutar en el EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            try {
                LoginView loginView = new LoginView();
                LoginController loginController = new LoginController(loginView);
                loginView.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Error al iniciar la aplicación: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
} 