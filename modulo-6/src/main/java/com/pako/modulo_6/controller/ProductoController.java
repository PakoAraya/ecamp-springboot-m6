package com.pako.modulo_6.controller;


import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.services.ProductoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

  private final ProductoService productoService;

  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @GetMapping("/")
  public List<ProductoDTO> obtenerProductoList(){
    List<ProductoDTO> lista= productoService.obtenerProductos();
    return lista;
  }

}
