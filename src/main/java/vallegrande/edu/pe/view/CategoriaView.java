package vallegrande.edu.pe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CategoriaView extends JFrame {
    public JTextField txtNombre, txtDescripcion;
    public JButton btnAgregar, btnModificar, btnEliminar, btnListar;
    public JTable tablaCategorias;
    public DefaultTableModel modeloTabla;

    public CategoriaView() {
        setTitle("Gestión de Categorías");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos de Categoría"));
        panelForm.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(16);
        gbc.gridx = 1;
        panelForm.add(txtNombre, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(new JLabel("Descripción:"), gbc);
        txtDescripcion = new JTextField(16);
        gbc.gridx = 1;
        panelForm.add(txtDescripcion, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        // Tabla
        String[] columnas = {"ID", "Nombre", "Descripción"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaCategorias = new JTable(modeloTabla);
        tablaCategorias.setRowHeight(24);
        JScrollPane scrollTabla = new JScrollPane(tablaCategorias);

        // Layout
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelForm, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);
        add(panelNorte, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
    }
} 