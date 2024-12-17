package com.pako.modulo_6.services;

import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.interfaces.ProductoService;
import com.pako.modulo_6.models.Producto;
import com.pako.modulo_6.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

  @Autowired
  ProductoRepository productoRepository;

  @Override //Este metodo reemplaza al codigo de abajo
  public List<ProductoDTO> obtenerProductos(){
    return this.productoRepository.obtenerLista().stream()
            .map(producto -> new ProductoDTO(producto))
            .collect(Collectors.toList());
  }

  /*Al trabajar directamente con JDBC ya no se necesita esta lista en bruto y conectamos
  a la base de datos directamente, por lo tanto, ya no necesitariamos de este codigo

  private List<Producto> productos = List.of(
          new Producto(1, "Laptop", "Laptop gaming de alto rendimiento", 1500.0, true),
          new Producto(2, "Smartphone", "Último modelo con tecnología 5G", 800.0, false),
          new Producto(3, "Teclado", "Teclado mecánico retroiluminado", 100.0, true)
  );

  @Override
  public List<ProductoDTO> obtenerProductos(){
    //streams!!!!
    return productos.stream()
            .map(producto -> new ProductoDTO(producto))
            .collect(Collectors.toList());
  }

*/

  @Override //Ahora el metodo cambia con JDBC
  public ProductoDTO guardarProducto(ProductoDTO nuevoProductoDTO){
    nuevoProductoDTO.setEnStock(true);
    this.productoRepository.guardarProducto(new Producto(nuevoProductoDTO));
    return nuevoProductoDTO;
  }
}