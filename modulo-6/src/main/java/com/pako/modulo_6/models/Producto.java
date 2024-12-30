package com.pako.modulo_6.models;

import com.pako.modulo_6.dtos.ProductoDTO;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

/***
 * @author Francisco Javier Araya H
 * @since 13-12-2024
 * @version 1.0.0
 */
@Entity
@Table(name = "producto")
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "nombre", nullable = false, length = 100)
  private String nombre;

  @Column(name = "descripcion", nullable = false, length = 100)
  private String descripcion;

  @Column(name = "precio", nullable = false)
  private double precio;

  @Column(name = "en_Stock", nullable = false)
  private boolean enStock;

  //donde tenga la FK dejare la notacion many to one
  @ManyToOne
  @JoinColumn(name = "categoria_id")
  private Categoria categoria;

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

  //Constructor para trabajar con ProductoServiceImple y el JDBC
  public Producto(ProductoDTO productoDTO) {
    this.id = productoDTO.getId();
    this.nombre = productoDTO.getNombre();
    this.descripcion = productoDTO.getDescripcion();
    this.precio = productoDTO.getPrecio();
    this.enStock = productoDTO.isEnStock();
    this.categoria = new Categoria(productoDTO.getCategoriaDTO());
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

  //Se agregan los getters y setters de la categoria
  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
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
