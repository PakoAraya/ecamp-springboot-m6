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
    return this.productoRepositoryJPA.findAll().stream()
            .map(producto -> new ProductoDTO(producto))
            .collect(Collectors.toList());
  }

  @Override //Ahora el metodo cambia con JPA usando el metodo SAVE
  public ProductoDTO guardarProducto(ProductoDTO nuevoProductoDTO){
    nuevoProductoDTO.setEnStock(true);
    this.productoRepositoryJPA.save(new Producto(nuevoProductoDTO));
    return nuevoProductoDTO;
  }
}