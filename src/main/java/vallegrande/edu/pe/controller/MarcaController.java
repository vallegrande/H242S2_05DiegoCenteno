package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.view.MarcaView;
import vallegrande.edu.pe.model.Marca;
import vallegrande.edu.pe.service.MarcaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MarcaController {
    private MarcaView view;
    private MarcaService service = new MarcaService();

    public MarcaController(MarcaView view) {
        this.view = view;
        this.view.btnAgregar.addActionListener(e -> agregarMarca());
        this.view.btnModificar.addActionListener(e -> modificarMarca());
        this.view.btnEliminar.addActionListener(e -> eliminarMarca());
        this.view.btnListar.addActionListener(e -> listarMarcas());
        this.view.tablaMarcas.getSelectionModel().addListSelectionListener(e -> cargarSeleccionado());
        listarMarcas();
    }

    private void agregarMarca() {
        String nombre = view.txtNombre.getText().trim();
        String descripcion = view.txtDescripcion.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(view, "El nombre de la marca es obligatorio.");
            return;
        }
        
        try {
            Marca marca = new Marca();
            marca.setNombre(nombre);
            marca.setDescripcion(descripcion);
            service.agregar(marca);
            JOptionPane.showMessageDialog(view, "Marca agregada correctamente.");
            limpiarCampos();
            listarMarcas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al agregar: " + ex.getMessage());
        }
    }

    private void modificarMarca() {
        int fila = view.tablaMarcas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(view, "Seleccione una marca para modificar.");
            return;
        }
        
        int id = Integer.parseInt(view.modeloTabla.getValueAt(fila, 0).toString());
        String nombre = view.txtNombre.getText().trim();
        String descripcion = view.txtDescripcion.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(view, "El nombre de la marca es obligatorio.");
            return;
        }
        
        try {
            Marca marca = new Marca();
            marca.setId(id);
            marca.setNombre(nombre);
            marca.setDescripcion(descripcion);
            service.modificar(marca);
            JOptionPane.showMessageDialog(view, "Marca modificada correctamente.");
            limpiarCampos();
            listarMarcas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al modificar: " + ex.getMessage());
        }
    }

    private void eliminarMarca() {
        int fila = view.tablaMarcas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(view, "Seleccione una marca para eliminar.");
            return;
        }
        
        int id = Integer.parseInt(view.modeloTabla.getValueAt(fila, 0).toString());
        String nombre = view.modeloTabla.getValueAt(fila, 1).toString();
        
        int confirmacion = JOptionPane.showConfirmDialog(view, 
            "¿Está seguro de eliminar la marca '" + nombre + "'?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                service.eliminar(id);
                JOptionPane.showMessageDialog(view, "Marca eliminada correctamente.");
                limpiarCampos();
                listarMarcas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al eliminar: " + ex.getMessage());
            }
        }
    }

    private void listarMarcas() {
        DefaultTableModel modelo = view.modeloTabla;
        modelo.setRowCount(0);
        try {
            List<Marca> lista = service.listar();
            for (Marca m : lista) {
                Object[] fila = new Object[3];
                fila[0] = m.getId();
                fila[1] = m.getNombre();
                fila[2] = m.getDescripcion();
                modelo.addRow(fila);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al listar: " + ex.getMessage());
        }
    }

    private void cargarSeleccionado() {
        int fila = view.tablaMarcas.getSelectedRow();
        if (fila != -1) {
            view.txtNombre.setText(view.modeloTabla.getValueAt(fila, 1).toString());
            view.txtDescripcion.setText(view.modeloTabla.getValueAt(fila, 2).toString());
        }
    }

    private void limpiarCampos() {
        view.txtNombre.setText("");
        view.txtDescripcion.setText("");
        view.tablaMarcas.clearSelection();
    }
} 