package com.pako.modulo_6.services;

import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.interfaces.ProductoService;
import com.pako.modulo_6.models.Producto;
import com.pako.modulo_6.repositorios.ProductoRepositoryJPA;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

  @Autowired
  private Validator validator;

  @Autowired
  ProductoRepositoryJPA productoRepositoryJPA;

  @Override //Este metodo cambia usando JPA y el metodo FINDALL
  public List<ProductoDTO> obtenerProductos() {

//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//    User userAuth = (User) principal;
//    System.out.println("Usuario que esta en sesion");
//    System.out.println(userAuth.getUsername());

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    System.out.println(authentication.getName());

    List<Producto> productos = this.productoRepositoryJPA.findAll();

    if (productos.isEmpty()) {
      throw new NoSuchElementException("No se encontraron productos");
    }
    return productos.stream()
            .map(producto -> new ProductoDTO(producto, true))
            .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public ProductoDTO guardarProducto(ProductoDTO nuevoProductoDTO) {
    //guarda

    //validacion contra informacion de entrada
    if (nuevoProductoDTO.getPrecio() <= 1000) {
      throw new IllegalStateException("Precio no debe ser menor o igual a 1000");
    }

    //validacion contra la informacion de bd
    List<Producto> producto = this.productoRepositoryJPA.findByNombre(nuevoProductoDTO.getNombre());
    if (!producto.isEmpty()) {
      throw new IllegalStateException("Nombre no puede estar duplicado");
    }


    nuevoProductoDTO.setEnStock(true);
    this.productoRepositoryJPA.save(new Producto(nuevoProductoDTO));
    return nuevoProductoDTO;
  }

  @Override
  public List<ProductoDTO> buscarProductoByNombre(String nombre) {
    return this.productoRepositoryJPA.findByNombre(nombre).stream()
            .map(producto -> new ProductoDTO(producto))
            .collect(Collectors.toList());
  }
}