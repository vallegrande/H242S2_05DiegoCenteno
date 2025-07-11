package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.view.MaterialEscolarView;
import vallegrande.edu.pe.database.Conexion;
import vallegrande.edu.pe.model.MaterialEscolar;
import vallegrande.edu.pe.service.MaterialEscolarService;
import vallegrande.edu.pe.service.MarcaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MaterialEscolarController {
    private MaterialEscolarView view;
    private MaterialEscolarService service = new MaterialEscolarService();
    private MarcaService marcaService = new MarcaService();

    public MaterialEscolarController(MaterialEscolarView view) {
        this.view = view;
        cargarMarcas();
        this.view.btnAgregar.addActionListener(e -> agregarMaterial());
        this.view.btnModificar.addActionListener(e -> modificarMaterial());
        this.view.btnEliminarLogico.addActionListener(e -> eliminarMaterial(true));
        this.view.btnEliminarFisico.addActionListener(e -> eliminarMaterial(false));
        this.view.btnListar.addActionListener(e -> listarMateriales());
        this.view.btnGestionarCategorias.addActionListener(e -> abrirGestionCategorias());
        this.view.btnGestionarProveedores.addActionListener(e -> abrirGestionMarcas());
        this.view.tablaMateriales.getSelectionModel().addListSelectionListener(e -> cargarSeleccionado());
        this.view.txtCantidadUnidad.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { actualizarTotal(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { actualizarTotal(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { actualizarTotal(); }
        });
        this.view.txtCantidadDocena.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { actualizarTotal(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { actualizarTotal(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { actualizarTotal(); }
        });
        this.view.txtPrecio.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { actualizarTotal(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { actualizarTotal(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { actualizarTotal(); }
        });
        listarMateriales();
    }

    private void cargarMarcas() {
        try {
            view.cbMarca.removeAllItems();
            for (vallegrande.edu.pe.model.Marca marca : marcaService.listar()) {
                view.cbMarca.addItem(marca.getNombre());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error al cargar marcas: " + e.getMessage());
        }
    }

    private void agregarMaterial() {
        String producto = view.txtProducto.getText();
        String categoria = (String) view.cbCategoria.getSelectedItem();
        String marca = (String) view.cbMarca.getSelectedItem();
        String cantidadUnidadStr = view.txtCantidadUnidad.getText();
        String cantidadDocenaStr = view.txtCantidadDocena.getText();
        String precioStr = view.txtPrecio.getText();
        String descripcion = view.txtDescripcion.getText();
        String stockMinimoStr = view.txtStockMinimo.getText();
        if (producto.isEmpty() || marca.isEmpty() || cantidadUnidadStr.isEmpty() || cantidadDocenaStr.isEmpty() || precioStr.isEmpty() || descripcion.isEmpty() || stockMinimoStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Complete todos los campos.");
            return;
        }
        int cantidadUnidad = 0;
        int cantidadDocena = 0;
        try {
            cantidadUnidad = cantidadUnidadStr.isEmpty() ? 0 : Integer.parseInt(cantidadUnidadStr);
            cantidadDocena = cantidadDocenaStr.isEmpty() ? 0 : Integer.parseInt(cantidadDocenaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Las cantidades deben ser números enteros.");
            return;
        }
        if ((cantidadUnidad > 0 && cantidadDocena > 0) || (cantidadUnidad == 0 && cantidadDocena == 0)) {
            JOptionPane.showMessageDialog(view, "Ingrese solo cantidad por unidad o solo por docena, no ambas a la vez.");
            return;
        }
        try {
            MaterialEscolar m = new MaterialEscolar();
            m.setProducto(producto);
            m.setDescripcion(descripcion);
            m.setCategoria(categoria);
            m.setMarca(marca);
            m.setCantidadUnidad(cantidadUnidad);
            m.setCantidadDocena(cantidadDocena);
            m.setPrecio(Double.parseDouble(precioStr));
            m.setStockMinimo(Integer.parseInt(stockMinimoStr));
            m.setEstado("Activo");
            m.setFechaRegistro(java.time.LocalDateTime.now());
            service.agregar(m);
            JOptionPane.showMessageDialog(view, "Material agregado correctamente.");
            limpiarCampos();
            listarMateriales();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al agregar: " + ex.getMessage());
        }
    }

    private void modificarMaterial() {
        int fila = view.tablaMateriales.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(view, "Seleccione un material para modificar.");
            return;
        }
        int id = Integer.parseInt(view.modeloTabla.getValueAt(fila, 0).toString());
        String producto = view.txtProducto.getText();
        String categoria = (String) view.cbCategoria.getSelectedItem();
        String marca = (String) view.cbMarca.getSelectedItem();
        String cantidadUnidadStr = view.txtCantidadUnidad.getText();
        String cantidadDocenaStr = view.txtCantidadDocena.getText();
        String precioStr = view.txtPrecio.getText();
        String descripcion = view.txtDescripcion.getText();
        String stockMinimoStr = view.txtStockMinimo.getText();
        if (producto.isEmpty() || marca.isEmpty() || cantidadUnidadStr.isEmpty() || cantidadDocenaStr.isEmpty() || precioStr.isEmpty() || descripcion.isEmpty() || stockMinimoStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Complete todos los campos.");
            return;
        }
        int cantidadUnidad = 0;
        int cantidadDocena = 0;
        try {
            cantidadUnidad = cantidadUnidadStr.isEmpty() ? 0 : Integer.parseInt(cantidadUnidadStr);
            cantidadDocena = cantidadDocenaStr.isEmpty() ? 0 : Integer.parseInt(cantidadDocenaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Las cantidades deben ser números enteros.");
            return;
        }
        if ((cantidadUnidad > 0 && cantidadDocena > 0) || (cantidadUnidad == 0 && cantidadDocena == 0)) {
            JOptionPane.showMessageDialog(view, "Ingrese solo cantidad por unidad o solo por docena, no ambas a la vez.");
            return;
        }
        try {
            MaterialEscolar m = new MaterialEscolar();
            m.setId(id);
            m.setProducto(producto);
            m.setDescripcion(descripcion);
            m.setCategoria(categoria);
            m.setMarca(marca);
            m.setCantidadUnidad(cantidadUnidad);
            m.setCantidadDocena(cantidadDocena);
            m.setPrecio(Double.parseDouble(precioStr));
            m.setStockMinimo(Integer.parseInt(stockMinimoStr));
            service.modificar(m);
            JOptionPane.showMessageDialog(view, "Material modificado correctamente.");
            limpiarCampos();
            listarMateriales();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al modificar: " + ex.getMessage());
        }
    }

    private void eliminarMaterial(boolean logico) {
        int fila = view.tablaMateriales.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(view, "Seleccione un material para eliminar.");
            return;
        }
        int id = Integer.parseInt(view.modeloTabla.getValueAt(fila, 0).toString());
        try {
            if (logico) {
                service.eliminarLogico(id);
                // Cambiar estado a 'Inactivo'
                service.cambiarEstado(id, "Inactivo");
            } else {
                service.eliminarFisico(id);
            }
            JOptionPane.showMessageDialog(view, logico ? "Eliminación lógica exitosa." : "Eliminación física exitosa.");
            limpiarCampos();
            listarMateriales();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void listarMateriales() {
        DefaultTableModel modelo = view.modeloTabla;
        modelo.setRowCount(0);
        try {
            java.util.List<MaterialEscolar> lista = service.listar();
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (MaterialEscolar m : lista) {
                Object[] fila = new Object[13];
                fila[0] = m.getId();
                fila[1] = m.getProducto();
                fila[2] = m.getDescripcion();
                fila[3] = m.getCategoria();
                fila[4] = m.getMarca();
                fila[5] = m.getCantidadUnidad();
                fila[6] = m.getCantidadDocena();
                fila[7] = m.getPrecio();
                // Calcular total
                double total = 0.0;
                if (m.getCantidadUnidad() > 0 && m.getCantidadDocena() == 0) {
                    total = m.getCantidadUnidad() * m.getPrecio();
                } else if (m.getCantidadDocena() > 0 && m.getCantidadUnidad() == 0) {
                    total = m.getCantidadDocena() * 12 * m.getPrecio();
                }
                fila[8] = String.format("%.2f", total);
                fila[9] = m.getStockMinimo();
                fila[10] = m.getEstado();
                fila[11] = m.getFechaRegistro().format(formatter);
                fila[12] = m.isEliminadoLogico() ? "Sí" : "No";
                modelo.addRow(fila);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al listar: " + ex.getMessage());
        }
    }

    private void cargarSeleccionado() {
        int fila = view.tablaMateriales.getSelectedRow();
        if (fila != -1) {
            view.txtProducto.setText(view.modeloTabla.getValueAt(fila, 1).toString());
            view.txtDescripcion.setText(view.modeloTabla.getValueAt(fila, 2).toString());
            view.cbCategoria.setSelectedItem(view.modeloTabla.getValueAt(fila, 3).toString());
            view.cbMarca.setSelectedItem(view.modeloTabla.getValueAt(fila, 4).toString());
            view.txtCantidadUnidad.setText(view.modeloTabla.getValueAt(fila, 5).toString());
            view.txtCantidadDocena.setText(view.modeloTabla.getValueAt(fila, 6).toString());
            view.txtPrecio.setText(view.modeloTabla.getValueAt(fila, 7).toString());
            view.txtStockMinimo.setText(view.modeloTabla.getValueAt(fila, 8).toString());
            String estado = view.modeloTabla.getValueAt(fila, 9).toString();
            if ("Activo".equalsIgnoreCase(estado)) {
                view.lblEstadoVisual.setText("Estado: Activo");
                view.lblEstadoVisual.setForeground(new java.awt.Color(27, 94, 32));
            } else if ("Inactivo".equalsIgnoreCase(estado)) {
                view.lblEstadoVisual.setText("Estado: Inactivo");
                view.lblEstadoVisual.setForeground(new java.awt.Color(183, 28, 28));
            } else {
                view.lblEstadoVisual.setText("");
            }
        } else {
            view.lblEstadoVisual.setText("");
        }
    }

    private void limpiarCampos() {
        view.txtProducto.setText("");
        view.txtDescripcion.setText("");
        view.txtStockMinimo.setText("");
        view.cbCategoria.setSelectedIndex(0);
        view.cbMarca.setSelectedIndex(0);
        view.txtCantidadUnidad.setText("");
        view.txtCantidadDocena.setText("");
        view.txtPrecio.setText("");
        view.txtTotal.setText("");
        view.tablaMateriales.clearSelection();
        view.lblEstadoVisual.setText("");
    }

    private void actualizarTotal() {
        String cantidadUnidadStr = view.txtCantidadUnidad.getText();
        String cantidadDocenaStr = view.txtCantidadDocena.getText();
        String precioStr = view.txtPrecio.getText();
        int cantidadUnidad = 0;
        int cantidadDocena = 0;
        double precio = 0.0;
        try {
            cantidadUnidad = cantidadUnidadStr.isEmpty() ? 0 : Integer.parseInt(cantidadUnidadStr);
            cantidadDocena = cantidadDocenaStr.isEmpty() ? 0 : Integer.parseInt(cantidadDocenaStr);
            precio = precioStr.isEmpty() ? 0.0 : Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            view.txtTotal.setText("");
            return;
        }
        double total = 0.0;
        if (cantidadUnidad > 0 && cantidadDocena == 0) {
            total = cantidadUnidad * precio;
        } else if (cantidadDocena > 0 && cantidadUnidad == 0) {
            total = cantidadDocena * 12 * precio;
        }
        view.txtTotal.setText(String.format("%.2f", total));
    }
    
    private void abrirGestionCategorias() {
        try {
            vallegrande.edu.pe.view.CategoriaView categoriaView = new vallegrande.edu.pe.view.CategoriaView();
            vallegrande.edu.pe.controller.CategoriaController categoriaController = new vallegrande.edu.pe.controller.CategoriaController(categoriaView);
            categoriaView.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error al abrir gestión de categorías: " + e.getMessage());
        }
    }
    
    private void abrirGestionMarcas() {
        try {
            vallegrande.edu.pe.view.MarcaView marcaView = new vallegrande.edu.pe.view.MarcaView();
            vallegrande.edu.pe.controller.MarcaController marcaController = new vallegrande.edu.pe.controller.MarcaController(marcaView);
            marcaView.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error al abrir gestión de marcas: " + e.getMessage());
        }
    }
} 