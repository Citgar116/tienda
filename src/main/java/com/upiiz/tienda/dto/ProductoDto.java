package com.upiiz.tienda.dto;

import com.upiiz.tienda.models.Producto;

public class ProductoDto {
    private String nombre;
    private float precio;
    private String categoria;

    public ProductoDto() {
    }

    public ProductoDto(String nombre, float precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
