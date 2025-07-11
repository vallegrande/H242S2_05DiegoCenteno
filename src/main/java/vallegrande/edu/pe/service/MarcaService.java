package vallegrande.edu.pe.service;

import vallegrande.edu.pe.database.Conexion;
import vallegrande.edu.pe.model.Marca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaService {
    
    public void agregar(Marca marca) throws SQLException {
        String sql = "INSERT INTO marca (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, marca.getNombre());
            pstmt.setString(2, marca.getDescripcion());
            pstmt.executeUpdate();
        }
    }
    
    public void modificar(Marca marca) throws SQLException {
        String sql = "UPDATE marca SET nombre = ?, descripcion = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, marca.getNombre());
            pstmt.setString(2, marca.getDescripcion());
            pstmt.setInt(3, marca.getId());
            pstmt.executeUpdate();
        }
    }
    
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM marca WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    public List<Marca> listar() throws SQLException {
        List<Marca> marcas = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion FROM marca ORDER BY nombre";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getInt("id"));
                marca.setNombre(rs.getString("nombre"));
                marca.setDescripcion(rs.getString("descripcion"));
                marcas.add(marca);
            }
        }
        return marcas;
    }
    
    public Marca buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nombre, descripcion FROM marca WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getInt("id"));
                marca.setNombre(rs.getString("nombre"));
                marca.setDescripcion(rs.getString("descripcion"));
                return marca;
            }
        }
        return null;
    }
    
    public Marca buscarPorNombre(String nombre) throws SQLException {
        String sql = "SELECT id, nombre, descripcion FROM marca WHERE nombre = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getInt("id"));
                marca.setNombre(rs.getString("nombre"));
                marca.setDescripcion(rs.getString("descripcion"));
                return marca;
            }
        }
        return null;
    }
} 