package com.pako.modulo_6.interfaces;

import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.models.Usuario;

import java.util.List;

public interface UsuarioService {
  List<UsuarioDTO> getAllUsuario(); // Devuelve la lista de usuarios
  UsuarioDTO getUsuarioById(int id); // Obtiene un usuario por ID
  UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO); // Guarda un usuario si es válido
  boolean validarUsuario(UsuarioDTO usuarioDTO); // Valida los datos de un usuario
  List<UsuarioDTO> buscarUsuarioPorEdad(); //Trae ordenados los usuario por edad asc
  List<UsuarioDTO> traerUsuarioBootcampCl(); //Trae usuarios cuyo correo termine en @bootcamp.cl
  List<UsuarioDTO> obtenerUsuariosActivos(); //Trae a todos los usuarios activos en el sistema
  List<UsuarioDTO> obtenerUsuariosMayoresDe(int edad); //Trae a todoss los usuarios mayores a una edad por parametro
  List<UsuarioDTO> obtenerUsuariosMayoresDeEdad(); //Trae a todos los usuarios mayores de 18 años
  List<UsuarioDTO> findByEdadBetween(int edadMin, int edadMax); // Trae a todos los usuarios dentro de rango de edad
  List<UsuarioDTO> obtenerUsuariosPorCorreo(String email); //Trae a un usuario en base a su correo electronico
}
