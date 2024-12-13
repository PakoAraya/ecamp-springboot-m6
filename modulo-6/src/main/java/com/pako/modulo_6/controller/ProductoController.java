package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.interfaces.ProductoService;
import com.pako.modulo_6.services.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {
  //@Autowired para generar inyecciones de dependencias de forma automatico
//    private final ProductoServiceImpl productoServiceImpl;///inyeccion por constructor antigua
  @Autowired
//    @Qualifier("") escoge que implementacion usar
  private ProductoService productoService;

  //    public ProductoController(ProductoServiceImpl productoServiceImpl) {
//   inyeccion por constructor antigua
//         this.productoServiceImpl = productoServiceImpl;
//        this.productoService=new ProductoServiceImpl();
//    }
  @GetMapping("/lista")
  public String mostrarLista(Model model) {
    List<ProductoDTO> productoLista = productoService.obtenerProductos();
    model.addAttribute("productos", productoLista);
    return "productos";
  }


  @GetMapping("/formulario")
  public String formulario(Model model) {
    model.addAttribute("productoDTO", new ProductoDTO());
    return "formulario-producto";
  }

  @PostMapping("/guardar")
  public String guardarProducto(
          @ModelAttribute("productoDTO") ProductoDTO productoDTO,
          Model model
  ) {
    System.out.println(productoDTO);
    //guardar
    ProductoDTO nuevoProductoDTO = productoService.guardarProducto(productoDTO);
    System.out.println(nuevoProductoDTO);

    model.addAttribute("nuevo", nuevoProductoDTO);

    return "nuevo-producto";
  }

}