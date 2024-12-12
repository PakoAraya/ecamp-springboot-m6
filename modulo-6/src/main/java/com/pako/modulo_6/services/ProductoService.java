package com.pako.modulo_6.services;

import com.pako.modulo_6.models.Producto;
import com.pako.modulo_6.dtos.ProductoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProductoService {
  private List<Producto> productos = List.of(
          new Producto(1, "Laptop", "Laptop gaming de alto rendimiento", 1500.0, true),
          new Producto(2, "Smartphone", "Último modelo con tecnología 5G", 800.0, false),
          new Producto(3, "Teclado", "Teclado mecánico retroiluminado", 100.0, true)
  );

  public List<ProductoDTO> obtenerProductos(){
    //streams!!!!
    return productos.stream()
            .map(producto -> new ProductoDTO(producto))
            .collect(Collectors.toList());
  }

}
