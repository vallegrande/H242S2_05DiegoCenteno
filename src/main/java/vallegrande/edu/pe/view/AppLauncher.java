package vallegrande.edu.pe.view;

import vallegrande.edu.pe.view.LoginView;
import vallegrande.edu.pe.controller.LoginController;
import vallegrande.edu.pe.view.MaterialEscolarView;
import vallegrande.edu.pe.controller.MaterialEscolarController;

public class AppLauncher {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView) {
                @Override
                public void onLoginSuccess() {
                    MaterialEscolarView mainView = new MaterialEscolarView();
                    new MaterialEscolarController(mainView);
                    mainView.setVisible(true);
                }
            };
            loginView.setVisible(true);
        });
    }
} 