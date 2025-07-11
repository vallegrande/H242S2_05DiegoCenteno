package vallegrande.edu.pe.model;

import java.time.LocalDateTime;

public class MaterialEscolar {
    private int id;
    private String producto;
    private String categoria;
    private String marca;
    private int cantidadUnidad;
    private int cantidadDocena;
    private double precio;
    private LocalDateTime fechaRegistro;
    private boolean eliminadoLogico;
    private String descripcion;
    private int stockMinimo;
    private String estado;

    public MaterialEscolar() {}

    public MaterialEscolar(int id, String producto, String categoria, String marca, int cantidadUnidad, int cantidadDocena, double precio, LocalDateTime fechaRegistro, boolean eliminadoLogico) {
        this.id = id;
        this.producto = producto;
        this.categoria = categoria;
        this.marca = marca;
        this.cantidadUnidad = cantidadUnidad;
        this.cantidadDocena = cantidadDocena;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
        this.eliminadoLogico = eliminadoLogico;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public int getCantidadUnidad() { return cantidadUnidad; }
    public void setCantidadUnidad(int cantidadUnidad) { this.cantidadUnidad = cantidadUnidad; }
    public int getCantidadDocena() { return cantidadDocena; }
    public void setCantidadDocena(int cantidadDocena) { this.cantidadDocena = cantidadDocena; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public boolean isEliminadoLogico() { return eliminadoLogico; }
    public void setEliminadoLogico(boolean eliminadoLogico) { this.eliminadoLogico = eliminadoLogico; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
} 