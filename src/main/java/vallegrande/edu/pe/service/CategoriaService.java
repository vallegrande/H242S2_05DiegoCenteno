package vallegrande.edu.pe.service;

import vallegrande.edu.pe.database.Conexion;
import vallegrande.edu.pe.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaService {
    
    public void agregar(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNombre());
            pstmt.setString(2, categoria.getDescripcion());
            pstmt.executeUpdate();
        }
    }
    
    public void modificar(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nombre = ?, descripcion = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNombre());
            pstmt.setString(2, categoria.getDescripcion());
            pstmt.setInt(3, categoria.getId());
            pstmt.executeUpdate();
        }
    }
    
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM categoria WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion FROM categoria ORDER BY nombre";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categorias.add(categoria);
            }
        }
        return categorias;
    }
    
    public Categoria buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nombre, descripcion FROM categoria WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                return categoria;
            }
        }
        return null;
    }
    
    public Categoria buscarPorNombre(String nombre) throws SQLException {
        String sql = "SELECT id, nombre, descripcion FROM categoria WHERE nombre = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                return categoria;
            }
        }
        return null;
    }
} 