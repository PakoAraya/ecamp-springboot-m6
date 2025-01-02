package com.pako.modulo_6.services;

import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.interfaces.ProductoService;
import com.pako.modulo_6.models.Producto;
import com.pako.modulo_6.repositorios.ProductoRepositoryJPA;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

  @Autowired
  ProductoRepositoryJPA productoRepositoryJPA;

  @Override //Este metodo cambia usando JPA y el metodo FINDALL
  public List<ProductoDTO> obtenerProductos() {
    List<Producto> productos = this.productoRepositoryJPA.findAll();

    if (productos.isEmpty()) {
      throw new IllegalStateException("No se encontraron productos");
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