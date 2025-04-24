package com.Productos.Produtos.producto.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @Column(unique=true)
    private String nombre;
    private String descripcion;
    private float precio;
    private LocalDate fechaRegistro;



public Producto() {}

    public Producto(Long idProducto, String nombre, String descripcion, float precio, LocalDate fechaRegistro) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
    }

    public Producto(String nombre, String descripcion, float precio, LocalDate fechaRegistro) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
