package com.pako.modulo_6.dtos;

import com.pako.modulo_6.models.Producto;

public class ProductoDTO {
  private int id;
  private String nombre;
  private String descripcion;
  private double precio;
  private boolean enStock;


  public ProductoDTO() {

  }

  public ProductoDTO(int id, String nombre, String descripcion, double precio, boolean enStock) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.enStock = enStock;
  }

  public ProductoDTO(Producto producto) {
    this.id = producto.getId();
    this.nombre = producto.getNombre();
    this.descripcion = producto.getDescripcion();
    this.precio = producto.getPrecio();
    this.enStock = producto.isEnStock();
  }

  public int getId() {
    return this.id;
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getDescripcion() {
    return this.descripcion;
  }

  public double getPrecio() {
    return this.precio;
  }

  public boolean isEnStock() {
    return this.enStock;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public void setEnStock(boolean enStock) {
    this.enStock = enStock;
  }

  @Override
  public String toString() {
    return "ProductoDTO{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", precio=" + precio +
            ", enStock=" + enStock +
            '}';
  }
}