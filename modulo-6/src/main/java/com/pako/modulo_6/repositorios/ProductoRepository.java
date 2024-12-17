package com.pako.modulo_6.repositorios;

import com.pako.modulo_6.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Los repository serian semejante (no igual) a los DAO
public class ProductoRepository {

  //Primero vamos a hacer una inyeccion
  private final JdbcTemplate jdbcTemplate;

  //Para que funcione hay que hacer un constructor
  public ProductoRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Producto> obtenerLista(){
    //Primero vamos a crear la query
    String sql = "SELECT * FROM producto";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Producto.class));
  }

  public void guardarProducto(Producto producto){
    String sql = "INSERT INTO producto VALUES(?,?,?,?)";
    jdbcTemplate.update(sql, producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.isEnStock());
    //queryForObject
  }


}
