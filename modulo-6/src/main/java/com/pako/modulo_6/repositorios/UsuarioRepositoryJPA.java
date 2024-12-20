package com.pako.modulo_6.repositorios;

import com.pako.modulo_6.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepositoryJPA extends JpaRepository<Usuario, Integer> {

  //Aqui debemos desarrollar los metodos que estaban en el Repository de Usuario (UsuarioRepository)
  //Se recomendaria eliminar la clase UsuarioRepository a futuro.

  //Traer todos los usuarios por edad para ordenar de menor a mayor
  List<Usuario> findAllByOrderByEdadAsc();

  //Traer todos los usuarios cuyo correo termine en @bootcamp.cl
  List<Usuario> findByEmailEndingWith(String email);

  //Query para traer a todos los usuarios activos
  @Query(value = "SELECT * FROM usuario u WHERE u.activo = TRUE", nativeQuery = true)
  List<Usuario> findUsuariosActivos();


  //Query para traer usuarios mayores de edad, usaremos a proposito Native Query
  @Query(value = "SELECT * FROM usuario u WHERE u.edad >= 18", nativeQuery = true)
  List<Usuario> findUsuariosMayoresDeEdad();

  //Query para traer el rango de edad (edadMon, edadMax), usaremos el metodo automatico
  //Aunque se podria haber hecho con native query
  List<Usuario> findByEdadBetween(int edadMin, int edadMax);

}
