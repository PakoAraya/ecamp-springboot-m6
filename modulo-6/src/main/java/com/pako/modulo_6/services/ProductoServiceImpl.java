package com.pako.modulo_6.services;

import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.interfaces.ProductoService;
import com.pako.modulo_6.models.Producto;
import com.pako.modulo_6.repositorios.ProductoRepository;
import com.pako.modulo_6.repositorios.ProductoRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

  @Autowired
  ProductoRepositoryJPA productoRepositoryJPA;

  @Override //Este metodo cambia usando JPA y el metodo FINDALL
  public List<ProductoDTO> obtenerProductos(){

    try{
      List<Producto> productos = productoRepositoryJPA.findAll();
      if(productos.isEmpty()){
        throw new Exception("Lista esta vacia");
      }

      return productos.stream()
              .map(producto -> new ProductoDTO(producto))
              .collect(Collectors.toList());

    }catch (Exception e){
      throw new RuntimeException("No se pudo obtener la lista de productos");
    }

  }

  @Override //Ahora el metodo cambia con JPA usando el metodo SAVE
  public ProductoDTO guardarProducto(ProductoDTO nuevoProductoDTO){

    try{

      //Validar que el objeto no sea nulo
      if(nuevoProductoDTO == null){
        throw new IllegalArgumentException("El nuevo producto no puede ser nulo");
      }

      //Asegurarse de establecer valores necesarios
      nuevoProductoDTO.setEnStock(true);

      //Guardar el producto utilizando el repositorio JPA
      Producto producto = new Producto(nuevoProductoDTO);
      Producto productoGuardado = this.productoRepositoryJPA.save(producto);

      //Retornar el DTO creado desde el producto guardado
      return new ProductoDTO(productoGuardado);

    } catch (IllegalArgumentException e) {
      throw new RuntimeException("Error de validación al guardar el producto: " + e.getMessage(), e);
    }catch (Exception e){
      throw new RuntimeException("No se pudo guardar el producto: " + e.getMessage(), e);
    }
  }
}