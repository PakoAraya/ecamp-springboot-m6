package com.pako.modulo_6.repositorios;

import com.pako.modulo_6.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

  //Query para saber de usuarios que son mayores de una edad por parametros
  @Query(value = "SELECT * FROM usuario u WHERE u.edad > :edad", nativeQuery = true)
  List<Usuario> findUsuariosMayoresDe(@Param("edad") int edad);

  //Query para traer usuarios mayores de edad (18 años), usaremos a proposito Native Query
  @Query(value = "SELECT * FROM usuario u WHERE u.edad >= 18", nativeQuery = true)
  List<Usuario> findUsuariosMayoresDeEdad();

  //Buscar usuarios por email
  @Query(value = "SELECT * FROM usuario u WHERE u.email = :email", nativeQuery = true)
  List<Usuario> findUsuarioByEmail(@Param("email") String email);

  //Query para contar a los usuarios activos dentro del sistema
  @Query(value = "SELECT COUNT(*) FROM usuario u WHERE u.activo = TRUE", nativeQuery = true)
  int findContarUsuariosActivos();

  //Query para traer el rango de edad (edadMon, edadMax), usaremos el metodo automatico
  //Aunque se podria haber hecho con native query
  List<Usuario> findByEdadBetween(int edadMin, int edadMax);

}
