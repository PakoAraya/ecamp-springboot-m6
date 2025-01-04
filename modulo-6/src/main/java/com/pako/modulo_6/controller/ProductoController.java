package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.interfaces.ProductoService;
import com.pako.modulo_6.services.ProductoServiceImpl;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  /***
   * Metodo que retorna lista completa de productos
   *
   * @author Francisco Javier Araya H
   * @param model parte de ui de spring
   * @return nombre del template a usar
   */
  @GetMapping("/lista")
  public String mostrarLista(Model model) {

    try{
      List<ProductoDTO> productoLista = productoService.obtenerProductos();
      model.addAttribute("productos", productoLista);
      return "productos";

    } catch (IllegalStateException e) {
      //Errores manuales (Aqui solo estamos mostrando el tipo de error en el navegador)
      model.addAttribute("error", e.getMessage());
      return "productos";
    }catch (Exception e) {
      //Excepcion general (sirve para to-do) (Lo mismo aqui pero con un error general)
      model.addAttribute("error", e.getMessage());
      return "productos";
    }
  }

  /***
   * Metodo que redirige a template de formulario
   *
   * @author Francisco Javier Araya H
   * @param model parte de ui de spring
   * @return retornamos el dto a usar en formulario, template del formulario
   */

  @PreAuthorize("hasRole('ADMIN')") //Esto asegura que solo el admin puede acceder a esta ruta e ingresar productos
  //@Secured({"ROLE_ADMIN","ROLE_SUPPORT"}) //Esto asegura que solo el admin puede acceder a esta ruta e ingresar productos y tambien otros roles
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

    try {
      if (result.hasErrors()) {
        model.addAttribute("error", "Error en envio de formulario");
        model.addAttribute("errores", result.getAllErrors());
        System.out.println(result.getAllErrors());
        return "formulario-producto";
      }

      ProductoDTO nuevoProductoDTO = this.productoService.guardarProducto(productoDTO);
      model.addAttribute("nuevo", nuevoProductoDTO);

      return "producto";


    } catch (IllegalStateException e) {
      System.out.println(e);
      model.addAttribute("error", e.getMessage());
      return "formulario-producto";
    } catch (ConstraintViolationException e) {
      model.addAttribute("error", e);
      return "formulario-producto";
    } catch (Exception e) {
      System.out.println(e);
      //Exception general sirve para todo tipo de errpr
      model.addAttribute("error", "Se genero un error");
      return "formulario-producto";
    }
  }
}