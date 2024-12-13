package com.pako.modulo_6.controller;


import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.models.Producto;
import com.pako.modulo_6.services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

  private final ProductoService productoService;

  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }


  @GetMapping("/lista")
  public String mostrarLista(Model model) {
    List<ProductoDTO> productoLista = productoService.obtenerProductos();
    model.addAttribute("productos", productoLista);
    return "productos";
  }

  @GetMapping("/formulario")
  public String formulario(Model model) {
    model.addAttribute("productoDTO",new ProductoDTO());
    return "formulario-producto";
  }

  @PostMapping("/guardar")
  public String guardarProducto(@ModelAttribute("productoDTO") ProductoDTO productoDTO, Model model){
    System.out.println(productoDTO);
    //guardar
    ProductoDTO nuevoProductoDTO= productoService.guardarProducto(productoDTO);
    System.out.println(nuevoProductoDTO);

    model.addAttribute("nuevo",nuevoProductoDTO);

    return "nuevo-producto";
  }
}