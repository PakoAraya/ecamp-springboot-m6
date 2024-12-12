package com.pako.modulo_6.controller;


import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller //Se cambia de @RestController a @Controller
@RequestMapping("/producto")
public class ProductoController {

  private final ProductoService productoService;

  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }

//  @GetMapping("/")
//  public List<ProductoDTO> obtenerProductoList(){
//    List<ProductoDTO> lista= productoService.obtenerProductos();
//    return lista;
//  }

  //Vamos a generar un nuevo request mapping para thymeleaf
  @GetMapping("/lista")
  public String mostrarLista(Model model) {
    List<ProductoDTO> productoLista = productoService.obtenerProductos();

    model.addAttribute("productos", productoLista);

    return "productos";
  }

}
