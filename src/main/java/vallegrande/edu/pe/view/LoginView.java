package vallegrande.edu.pe.view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public JTextField txtUsuario;
    public JPasswordField txtPassword;
    public JButton btnIngresar;

    private void estilizarBoton(JButton boton) {
        boton.setFocusPainted(false);
        boton.setBackground(new Color(76, 175, 80)); // Verde suave
        boton.setForeground(Color.WHITE);
        boton.setBorder(BorderFactory.createLineBorder(new Color(56, 142, 60), 1));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    public LoginView() {
        setTitle("Login - Gestión de Materiales Escolares");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 220);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        GridBagConstraints pgbc = new GridBagConstraints();
        pgbc.insets = new Insets(6, 6, 6, 6);
        pgbc.fill = GridBagConstraints.HORIZONTAL;
        pgbc.gridx = 0; pgbc.gridy = 0; pgbc.gridwidth = 2;
        JLabel titulo = new JLabel("Iniciar Sesión", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(new Color(56, 142, 60));
        panel.add(titulo, pgbc);

        pgbc.gridwidth = 1;
        pgbc.gridy = 1; pgbc.gridx = 0;
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblUsuario, pgbc);
        pgbc.gridx = 1;
        txtUsuario = new JTextField(14);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(txtUsuario, pgbc);

        pgbc.gridy = 2; pgbc.gridx = 0;
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblPassword, pgbc);
        pgbc.gridx = 1;
        txtPassword = new JPasswordField(14);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(txtPassword, pgbc);

        pgbc.gridy = 3; pgbc.gridx = 0; pgbc.gridwidth = 2;
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setPreferredSize(new Dimension(110, 30));
        estilizarBoton(btnIngresar);
        panel.add(btnIngresar, pgbc);

        gbc.gridx = 0; gbc.gridy = 0;
        add(panel, gbc);
    }
} 