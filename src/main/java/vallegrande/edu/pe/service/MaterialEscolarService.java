package vallegrande.edu.pe.service;

import vallegrande.edu.pe.model.MaterialEscolar;
import vallegrande.edu.pe.database.Conexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MaterialEscolarService {
    public void agregar(MaterialEscolar material) throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "INSERT INTO materiales_escolares (producto, descripcion, categoria, marca, cantidad_unidad, cantidad_docena, precio, stock_minimo, estado, fecha_registro, eliminado_logico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, material.getProducto());
            ps.setString(2, material.getDescripcion());
            ps.setString(3, material.getCategoria());
            ps.setString(4, material.getMarca());
            ps.setInt(5, material.getCantidadUnidad());
            ps.setInt(6, material.getCantidadDocena());
            ps.setDouble(7, material.getPrecio());
            ps.setInt(8, material.getStockMinimo());
            ps.setString(9, material.getEstado());
            ps.setTimestamp(10, Timestamp.valueOf(material.getFechaRegistro()));
            ps.executeUpdate();
        }
    }

    public void modificar(MaterialEscolar material) throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "UPDATE materiales_escolares SET producto=?, descripcion=?, categoria=?, marca=?, cantidad_unidad=?, cantidad_docena=?, precio=?, stock_minimo=?, estado=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, material.getProducto());
            ps.setString(2, material.getDescripcion());
            ps.setString(3, material.getCategoria());
            ps.setString(4, material.getMarca());
            ps.setInt(5, material.getCantidadUnidad());
            ps.setInt(6, material.getCantidadDocena());
            ps.setDouble(7, material.getPrecio());
            ps.setInt(8, material.getStockMinimo());
            ps.setString(9, material.getEstado());
            ps.setInt(10, material.getId());
            ps.executeUpdate();
        }
    }

    public void eliminarLogico(int id) throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "UPDATE materiales_escolares SET eliminado_logico=true WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void eliminarFisico(int id) throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "DELETE FROM materiales_escolares WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void cambiarEstado(int id, String estado) throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "UPDATE materiales_escolares SET estado=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public List<MaterialEscolar> listar() throws SQLException {
        List<MaterialEscolar> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT * FROM materiales_escolares";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                MaterialEscolar m = new MaterialEscolar();
                m.setId(rs.getInt("id"));
                m.setProducto(rs.getString("producto"));
                m.setCategoria(rs.getString("categoria"));
                m.setMarca(rs.getString("marca"));
                m.setCantidadUnidad(rs.getInt("cantidad_unidad"));
                m.setCantidadDocena(rs.getInt("cantidad_docena"));
                m.setPrecio(rs.getDouble("precio"));
                m.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
                m.setEliminadoLogico(rs.getBoolean("eliminado_logico"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setStockMinimo(rs.getInt("stock_minimo"));
                m.setEstado(rs.getString("estado"));
                lista.add(m);
            }
        }
        return lista;
    }
} 