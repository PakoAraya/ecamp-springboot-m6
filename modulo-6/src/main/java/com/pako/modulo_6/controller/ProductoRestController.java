package com.pako.modulo_6.controller;


import com.pako.modulo_6.dtos.CategoriaDTO;
import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.interfaces.CategoriaService;
import com.pako.modulo_6.interfaces.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/producto")
public class ProductoRestController {

  @Autowired
  private ProductoService productoService;

  @Autowired
  private CategoriaService categoriaService;

  @GetMapping("/categoria/lista")
  public List<CategoriaDTO> mostrarCategorias(){
    return this.categoriaService.obtenerCategorias();
  }

  @GetMapping("/lista")
  public List<ProductoDTO> mostrarLista(){
    return this.productoService.obtenerProductos();
  }

  @PostMapping("/guardar")
  public ProductoDTO guardarProducto(ProductoDTO productoDTO){
    return this.productoService.guardarProducto(productoDTO);
  }
}
