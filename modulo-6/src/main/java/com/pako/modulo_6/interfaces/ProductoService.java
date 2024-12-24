package com.pako.modulo_6.interfaces;

import com.pako.modulo_6.dtos.ProductoDTO;
import java.util.List;

public interface ProductoService {

  List<ProductoDTO> obtenerProductos();
  ProductoDTO guardarProducto(ProductoDTO nuevoProductoDTO);
  List<ProductoDTO> buscarProductoByNombre(String nombre);
}