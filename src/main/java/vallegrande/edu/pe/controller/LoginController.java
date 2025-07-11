package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.view.LoginView;
import vallegrande.edu.pe.database.Conexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
        this.view.btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    public void onLoginSuccess() {
        // Este método puede ser sobreescrito para mostrar la ventana principal
    }

    private void login() {
        String usuario = view.txtUsuario.getText();
        String password = new String(view.txtPassword.getPassword());
        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, complete todos los campos.");
            return;
        }
        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(view, "¡Bienvenido, " + usuario + "!");
                view.dispose();
                onLoginSuccess();
            } else {
                JOptionPane.showMessageDialog(view, "Usuario o contraseña incorrectos.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error de conexión: " + ex.getMessage());
        }
    }
} 