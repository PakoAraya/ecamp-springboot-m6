package com.pako.modulo_6.controller;


import com.pako.modulo_6.dtos.CategoriaDTO;
import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.dtos.UsuarioLoginDTO;
import com.pako.modulo_6.interfaces.CategoriaService;
import com.pako.modulo_6.interfaces.ProductoService;
import com.pako.modulo_6.services.UsuarioLoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/producto")
public class ProductoRestController {

  @Autowired
  private ProductoService productoService;

  @Autowired
  private CategoriaService categoriaService;

  @Autowired
  private UsuarioLoginService usuarioLoginService;

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

  @PostMapping("/user/add")
  public UsuarioLoginDTO add(@Valid @RequestBody UsuarioLoginDTO usuarioDTO) {
    try {
      return usuarioLoginService.addUser(usuarioDTO);
    } catch (IllegalArgumentException e) {
      return new UsuarioLoginDTO();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
