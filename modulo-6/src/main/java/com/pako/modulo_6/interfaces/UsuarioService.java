package com.pako.modulo_6.interfaces;

import com.pako.modulo_6.dtos.UsuarioDTO;
import java.util.List;

public interface UsuarioService {
  List<UsuarioDTO> getAllUsuario(); // Devuelve la lista de usuarios
  UsuarioDTO getUsuarioById(int id); // Obtiene un usuario por ID
  UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO); // Guarda un usuario si es válido
  boolean validarUsuario(UsuarioDTO usuarioDTO); // Valida los datos de un usuario
  List<UsuarioDTO> buscarUsuarioPorEdad(); //Trae ordenados los usuario por edad asc
  List<UsuarioDTO> traerUsuarioBootcampCl(); //Trae usuarios cuyo correo termine en @bootcamp.cl
}
