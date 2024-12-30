package com.pako.modulo_6.dtos;

import com.pako.modulo_6.models.Categoria;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaDTO {
  private int id;
  private String nombre;
  private List<ProductoDTO> productoDTOList;

  public CategoriaDTO() {}

  public CategoriaDTO(Categoria categoria) {
    this.id = categoria.getId();
    this.nombre = categoria.getNombre();
//    this.productoDTOList = categoria.getProductoList()
//            .stream()
//            .map(producto -> new ProductoDTO(producto))
//            .collect(Collectors.toList());
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

//  public List<ProductoDTO> getProductoDTOList() {
//    return productoDTOList;
//  }
//
//  public void setProductoDTOList(List<ProductoDTO> productoDTOList) {
//    this.productoDTOList = productoDTOList;
//  }

  @Override
  public String toString() {
    return "CategoriaDTO(id=" + this.getId() + ", nombre=" + this.getNombre() + ")";
  }
}
