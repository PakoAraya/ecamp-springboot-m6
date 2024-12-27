package com.pako.modulo_6.dtos;

import com.pako.modulo_6.models.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaDTO {
  private int id;
  private String nombre;

  public CategoriaDTO() {}

  public CategoriaDTO(Categoria categoria) {
    this.id = categoria.getId();
    this.nombre = categoria.getNombre();
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
    return "CategoriaDTO(id=" + this.getId() + ", nombre=" + this.getNombre() + ")";
  }
}
