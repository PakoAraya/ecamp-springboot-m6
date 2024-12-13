package com.pako.modulo_6.models;

import org.springframework.stereotype.Component;

/***
 * @author Francisco Javier Araya H
 * @since 13-12-2024
 * @version 1.0.0
 */
@Component
public class Producto {
  private int id;
  private String nombre;
  private String descripcion;
  private double precio;
  private boolean enStock;

  /***
   * Modelo que representa abstraccion de un producto
   *
   * @param id
   * @param nombre
   * @param descripcion
   * @param precio
   * @param enStock => Atributo que indica si el producto cuenta con stock disponible
   */
  public Producto(int id, String nombre, String descripcion, double precio, boolean enStock) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.enStock = enStock;
  }

  public Producto() {

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
    return "Producto{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", precio=" + precio +
            ", enStock=" + enStock +
            '}';
  }
}
