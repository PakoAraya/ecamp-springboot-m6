package com.pako.modulo_6.models;

import com.pako.modulo_6.dtos.CategoriaDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "nombre", nullable = false)
  private String nombre;

  public Categoria(){}

  public Categoria(CategoriaDTO categoriaDTO) {
    this.id = categoriaDTO.getId();
    this.nombre = categoriaDTO.getNombre();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    return "Categoria(id=" + this.getId() + ", nombre=" + this.getNombre() + ")";
  }
}
