package com.pako.modulo_6.repositorios;

import com.pako.modulo_6.models.Producto;
import com.pako.modulo_6.models.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {
  //Crear el Jdbc Template
  private final JdbcTemplate jdbcTemplate;

  //Creamos el constructor de la clase
  public UsuarioRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  //Metodo para traer por query todos los usuarios
  public List<Usuario> getAllUsuario(){
    String sql = "SELECT * FROM usuario;";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usuario.class));
  }

  //Metodo para guardar un nuevo usuario
  public void guardarUsuario(Usuario usuario){
    String sql = "INSERT INTO usuario (nombre, email, edad) VALUES (?, ?, ?);";
    jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getEdad());
  }

}