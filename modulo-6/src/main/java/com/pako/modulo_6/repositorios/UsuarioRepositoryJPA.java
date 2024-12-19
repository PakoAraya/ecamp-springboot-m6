package com.pako.modulo_6.repositorios;

import com.pako.modulo_6.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepositoryJPA extends JpaRepository<Usuario, Integer> {

  //Aqui debemos desarrollar los metodos que estaban en el Repository de Usuario (UsuarioRepository)
  //Se recomendaria eliminar la clase UsuarioRepository a futuro.

  //Traer todos los usuarios por edad para ordenar de menor a mayor
  List<Usuario> findAllByOrderByEdadAsc();

  //Traer todos los usuarios cuyo correo termine en @bootcamp.cl
  List<Usuario> findByEmailEndingWith(String email);

}
