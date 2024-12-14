package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.interfaces.ProductoService;
import com.pako.modulo_6.services.ProductoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

  /***
   * Metodo que retorna lista completa de productos
   *
   * @author Francisco Javier Araya H
   * @param model parte de ui de spring
   * @return nombre del template a usar
   */
  @GetMapping("/lista")
  public String mostrarLista(Model model) {
    List<ProductoDTO> productoLista = productoService.obtenerProductos();
    model.addAttribute("productos", productoLista);
    return "productos";
  }

  /***
   * Metodo que redirige a template de formulario
   *
   * @author Francisco Javier Araya H
   * @param model parte de ui de spring
   * @return retornamos el dto a usar en formulario, template del formulario
   */

  @GetMapping("/formulario")
  public String formulario(Model model) {
    model.addAttribute("productoDTO", new ProductoDTO());
    return "formulario-producto";
  }

  @PostMapping("/guardar")
  public String guardarProducto(
          @Valid
          @ModelAttribute("productoDTO") ProductoDTO productoDTO,
          BindingResult result,
          Model model
  ) {
    //Esto es para capturar o atrapar errores
      if (result.hasErrors()) {
        model.addAttribute("error", "Error en envio de formulario");
        model.addAttribute("errores", result.getAllErrors());
        System.out.println(result.getAllErrors());
        return "formulario-producto";
      }

    System.out.println(productoDTO);
    //guardar
    ProductoDTO nuevoProductoDTO = productoService.guardarProducto(productoDTO);
    System.out.println(nuevoProductoDTO);

    model.addAttribute("nuevo", nuevoProductoDTO);

    return "nuevo-producto";
  }

}