package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.view.CategoriaView;
import vallegrande.edu.pe.model.Categoria;
import vallegrande.edu.pe.service.CategoriaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CategoriaController {
    private CategoriaView view;
    private CategoriaService service = new CategoriaService();

    public CategoriaController(CategoriaView view) {
        this.view = view;
        this.view.btnAgregar.addActionListener(e -> agregarCategoria());
        this.view.btnModificar.addActionListener(e -> modificarCategoria());
        this.view.btnEliminar.addActionListener(e -> eliminarCategoria());
        this.view.btnListar.addActionListener(e -> listarCategorias());
        this.view.tablaCategorias.getSelectionModel().addListSelectionListener(e -> cargarSeleccionado());
        listarCategorias();
    }

    private void agregarCategoria() {
        String nombre = view.txtNombre.getText().trim();
        String descripcion = view.txtDescripcion.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(view, "El nombre de la categoría es obligatorio.");
            return;
        }
        
        try {
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            categoria.setDescripcion(descripcion);
            service.agregar(categoria);
            JOptionPane.showMessageDialog(view, "Categoría agregada correctamente.");
            limpiarCampos();
            listarCategorias();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al agregar: " + ex.getMessage());
        }
    }

    private void modificarCategoria() {
        int fila = view.tablaCategorias.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(view, "Seleccione una categoría para modificar.");
            return;
        }
        
        int id = Integer.parseInt(view.modeloTabla.getValueAt(fila, 0).toString());
        String nombre = view.txtNombre.getText().trim();
        String descripcion = view.txtDescripcion.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(view, "El nombre de la categoría es obligatorio.");
            return;
        }
        
        try {
            Categoria categoria = new Categoria();
            categoria.setId(id);
            categoria.setNombre(nombre);
            categoria.setDescripcion(descripcion);
            service.modificar(categoria);
            JOptionPane.showMessageDialog(view, "Categoría modificada correctamente.");
            limpiarCampos();
            listarCategorias();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al modificar: " + ex.getMessage());
        }
    }

    private void eliminarCategoria() {
        int fila = view.tablaCategorias.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(view, "Seleccione una categoría para eliminar.");
            return;
        }
        
        int id = Integer.parseInt(view.modeloTabla.getValueAt(fila, 0).toString());
        String nombre = view.modeloTabla.getValueAt(fila, 1).toString();
        
        int confirmacion = JOptionPane.showConfirmDialog(view, 
            "¿Está seguro de eliminar la categoría '" + nombre + "'?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                service.eliminar(id);
                JOptionPane.showMessageDialog(view, "Categoría eliminada correctamente.");
                limpiarCampos();
                listarCategorias();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al eliminar: " + ex.getMessage());
            }
        }
    }

    private void listarCategorias() {
        DefaultTableModel modelo = view.modeloTabla;
        modelo.setRowCount(0);
        try {
            List<Categoria> lista = service.listar();
            for (Categoria c : lista) {
                Object[] fila = new Object[3];
                fila[0] = c.getId();
                fila[1] = c.getNombre();
                fila[2] = c.getDescripcion();
                modelo.addRow(fila);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al listar: " + ex.getMessage());
        }
    }

    private void cargarSeleccionado() {
        int fila = view.tablaCategorias.getSelectedRow();
        if (fila != -1) {
            view.txtNombre.setText(view.modeloTabla.getValueAt(fila, 1).toString());
            view.txtDescripcion.setText(view.modeloTabla.getValueAt(fila, 2).toString());
        }
    }

    private void limpiarCampos() {
        view.txtNombre.setText("");
        view.txtDescripcion.setText("");
        view.tablaCategorias.clearSelection();
    }
} 