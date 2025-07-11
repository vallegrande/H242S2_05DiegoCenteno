package vallegrande.edu.pe.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class MaterialEscolarView extends JFrame {
    public JTextField txtProducto, txtCantidadUnidad, txtCantidadDocena, txtPrecio, txtTotal;
    public JComboBox<String> cbCategoria;
    public JButton btnAgregar, btnModificar, btnEliminarLogico, btnEliminarFisico, btnListar, btnGestionarCategorias, btnGestionarProveedores;
    public JTable tablaMateriales;
    public DefaultTableModel modeloTabla;
    public JTextField txtDescripcion, txtStockMinimo;
    public JLabel lblEstadoVisual = new JLabel("");
    public JComboBox<String> cbMarca;

    public MaterialEscolarView() {
        setTitle("Gestión de Materiales Escolares");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de formulario en dos columnas, fondo blanco, borde moderno
        JPanel panelForm = new JPanel(new GridBagLayout()) {
            @Override
            public Dimension getPreferredSize() {
                // Ocupa el 60% del ancho y 60% del alto de la ventana
                Dimension parent = getParent() != null ? getParent().getSize() : new Dimension(1200, 600);
                int w = (int) (parent.width * 0.6);
                int h = (int) (parent.height * 0.6);
                return new Dimension(Math.max(w, 700), Math.max(h, 320));
            }
        };
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 2, true),
            BorderFactory.createEmptyBorder(16, 24, 16, 24)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);
        Dimension fieldDim = new Dimension(220, 32);

        // Campos proporcionados y más grandes
        txtProducto = new JTextField(10);
        txtProducto.setFont(fieldFont);
        txtProducto.setPreferredSize(fieldDim);
        cbCategoria = new JComboBox<>(new String[]{"Cuaderno", "Lapicero", "Mochila", "Libro", "Otro"});
        cbCategoria.setFont(fieldFont);
        cbCategoria.setPreferredSize(fieldDim);
        cbMarca = new JComboBox<>();
        cbMarca.setFont(fieldFont);
        cbMarca.setPreferredSize(fieldDim);
        txtCantidadUnidad = new JTextField(10);
        txtCantidadUnidad.setFont(fieldFont);
        txtCantidadUnidad.setPreferredSize(fieldDim);
        txtCantidadDocena = new JTextField(10);
        txtCantidadDocena.setFont(fieldFont);
        txtCantidadDocena.setPreferredSize(fieldDim);
        txtPrecio = new JTextField(10);
        txtPrecio.setFont(fieldFont);
        txtPrecio.setPreferredSize(fieldDim);
        txtDescripcion = new JTextField(18);
        txtDescripcion.setFont(fieldFont);
        txtDescripcion.setPreferredSize(new Dimension(2*fieldDim.width+32, 32));
        txtStockMinimo = new JTextField(10);
        txtStockMinimo.setFont(fieldFont);
        txtStockMinimo.setPreferredSize(fieldDim);
        txtTotal = new JTextField(10);
        txtTotal.setFont(fieldFont);
        txtTotal.setPreferredSize(fieldDim);
        txtTotal.setEditable(false);
        txtTotal.setBackground(new Color(235, 235, 235));

        // Fila 1: Producto | Cantidad (Docena)
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST; JLabel lblProducto = new JLabel("Producto:"); lblProducto.setFont(labelFont); panelForm.add(lblProducto, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; panelForm.add(txtProducto, gbc);
        gbc.gridx = 2; gbc.anchor = GridBagConstraints.EAST; JLabel lblDocena = new JLabel("Cantidad (Docena):"); lblDocena.setFont(labelFont); panelForm.add(lblDocena, gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST; panelForm.add(txtCantidadDocena, gbc);

        // Fila 2: Categoría | Precio
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST; JLabel lblCategoria = new JLabel("Categoría:"); lblCategoria.setFont(labelFont); panelForm.add(lblCategoria, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; panelForm.add(cbCategoria, gbc);
        gbc.gridx = 2; gbc.anchor = GridBagConstraints.EAST; JLabel lblPrecio = new JLabel("Precio:"); lblPrecio.setFont(labelFont); panelForm.add(lblPrecio, gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST; panelForm.add(txtPrecio, gbc);

        // Fila 3: Marca | Total
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST; JLabel lblMarca = new JLabel("Marca:"); lblMarca.setFont(labelFont); panelForm.add(lblMarca, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; panelForm.add(cbMarca, gbc);
        gbc.gridx = 2; gbc.anchor = GridBagConstraints.EAST; JLabel lblTotal = new JLabel("Total:"); lblTotal.setFont(labelFont); panelForm.add(lblTotal, gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST; panelForm.add(txtTotal, gbc);

        // Fila 4: Cantidad (Unidad) | Stock mínimo
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST; JLabel lblUnidad = new JLabel("Cantidad (Unidad):"); lblUnidad.setFont(labelFont); panelForm.add(lblUnidad, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; panelForm.add(txtCantidadUnidad, gbc);
        gbc.gridx = 2; gbc.anchor = GridBagConstraints.EAST; JLabel lblStock = new JLabel("Stock mínimo:"); lblStock.setFont(labelFont); panelForm.add(lblStock, gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST; panelForm.add(txtStockMinimo, gbc);

        // Fila 5: Descripción (a lo largo)
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4; gbc.anchor = GridBagConstraints.EAST; JLabel lblDescripcion = new JLabel("Descripcion :"); lblDescripcion.setFont(labelFont); panelForm.add(lblDescripcion, gbc);
        gbc.gridy = 5; gbc.anchor = GridBagConstraints.WEST; panelForm.add(txtDescripcion, gbc);
        gbc.gridwidth = 1;

        // Estado visual debajo del formulario
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 4; gbc.anchor = GridBagConstraints.CENTER;
        panelForm.add(lblEstadoVisual, gbc);
        gbc.gridwidth = 1;

        // Panel de botones centrado debajo del formulario
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(240, 255, 240));
        Dimension btnSize = new Dimension(130, 32);
        btnAgregar = new JButton("Agregar"); btnAgregar.setPreferredSize(btnSize);
        btnModificar = new JButton("Modificar"); btnModificar.setPreferredSize(btnSize);
        btnEliminarLogico = new JButton("Eliminar Lógico"); btnEliminarLogico.setPreferredSize(btnSize);
        btnEliminarFisico = new JButton("Eliminar Físico"); btnEliminarFisico.setPreferredSize(btnSize);
        btnListar = new JButton("Listar"); btnListar.setPreferredSize(btnSize);
        btnGestionarCategorias = new JButton("Gestionar Categorías"); btnGestionarCategorias.setPreferredSize(new Dimension(150, 32));
        btnGestionarProveedores = new JButton("Gestionar Marcas"); btnGestionarProveedores.setPreferredSize(new Dimension(150, 32));
        
        estilizarBoton(btnAgregar);
        estilizarBoton(btnModificar);
        estilizarBoton(btnEliminarLogico);
        estilizarBoton(btnEliminarFisico);
        estilizarBoton(btnListar);
        estilizarBoton(btnGestionarCategorias);
        estilizarBoton(btnGestionarProveedores);
        
        // Panel de botones principales
        panelBotones.add(btnAgregar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnModificar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnEliminarLogico);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnEliminarFisico);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnListar);
        
        // Panel de botones de gestión separado
        JPanel panelGestion = new JPanel();
        panelGestion.setBackground(new Color(240, 255, 240));
        panelGestion.add(btnGestionarCategorias);
        panelGestion.add(Box.createHorizontalStrut(10));
        panelGestion.add(btnGestionarProveedores);

        // Tabla mejorada
        String[] columnas = {"ID", "Producto", "Descripción", "Categoría", "Marca", "Unidad", "Docena", "Precio", "Total", "Stock Mínimo", "Estado", "Fecha Registro", "Eliminado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaMateriales = new JTable(modeloTabla);
        tablaMateriales.setRowHeight(28);
        tablaMateriales.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaMateriales.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane scrollTabla = new JScrollPane(tablaMateriales);
        scrollTabla.setPreferredSize(new Dimension(1000, 350));

        // Renderer de color para la columna Estado (ahora columna 10)
        tablaMateriales.getColumnModel().getColumn(10).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String estado = value != null ? value.toString() : "";
                if ("Activo".equalsIgnoreCase(estado)) {
                    c.setForeground(new Color(27, 94, 32)); // Verde oscuro
                } else if ("Inactivo".equalsIgnoreCase(estado)) {
                    c.setForeground(new Color(183, 28, 28)); // Rojo
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        // Título grande y centrado arriba del formulario
        JLabel titulo = new JLabel("Gestión de Materiales Escolares", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(27, 94, 32));
        titulo.setBorder(BorderFactory.createEmptyBorder(16, 0, 16, 0));

        // Layout principal
        JPanel panelNorte = new JPanel(new GridBagLayout());
        panelNorte.setBackground(new Color(210, 255, 210)); // Verde claro
        GridBagConstraints northGbc = new GridBagConstraints();
        northGbc.gridx = 0;
        northGbc.gridy = 0;
        northGbc.anchor = GridBagConstraints.CENTER;
        northGbc.weightx = 1;
        northGbc.weighty = 0;
        northGbc.fill = GridBagConstraints.HORIZONTAL;
        panelNorte.add(titulo, northGbc);
        northGbc.gridy = 1;
        northGbc.weighty = 1;
        northGbc.fill = GridBagConstraints.BOTH;
        panelNorte.add(panelForm, northGbc);
        northGbc.gridy = 2;
        northGbc.weighty = 0;
        northGbc.fill = GridBagConstraints.NONE;
        panelNorte.add(panelBotones, northGbc);
        northGbc.gridy = 3;
        northGbc.weighty = 0;
        northGbc.fill = GridBagConstraints.NONE;
        panelNorte.add(panelGestion, northGbc);

        add(panelNorte, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        getContentPane().setBackground(new Color(210, 255, 210)); // Fondo general verde claro
        lblEstadoVisual.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblEstadoVisual.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstadoVisual.setPreferredSize(new Dimension(180, 24));
    }

    private void estilizarBoton(JButton boton) {
        boton.setFocusPainted(false);
        boton.setBackground(new Color(46, 204, 113)); // Verde
        boton.setForeground(Color.WHITE);
        boton.setBorder(BorderFactory.createLineBorder(new Color(39, 174, 96), 2));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(39, 174, 96)); // Verde más oscuro
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(46, 204, 113)); // Verde normal
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(30, 132, 73)); // Verde aún más oscuro
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(39, 174, 96));
            }
        });
    }
} 