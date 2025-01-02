package com.pako.modulo_6.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pako.modulo_6.dtos.CategoriaDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "categoria")
public class Categoria {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "nombre", nullable = false)
  private String nombre;

  //Traer productos relacionados de producto
  @OneToMany(mappedBy = "categoria")
  @JsonManagedReference
  List<Producto> productoList;

  public Categoria(){}

  public Categoria(CategoriaDTO categoriaDTO) {
    this.id = categoriaDTO.getId();
    this.nombre = categoriaDTO.getNombre();
    this.productoList = categoriaDTO.getProductoDTOList()
            .stream()
            .map(productoDTO -> new Producto(productoDTO))
            .collect(Collectors.toList());
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

  public List<Producto> getProductoList() {
    return productoList;
  }

  public void setProductoList(List<Producto> productoList) {
    this.productoList = productoList;
  }

  @Override
  public String toString() {
    return "Categoria(id=" + this.getId() + ", nombre=" + this.getNombre() + ")";
  }
}
