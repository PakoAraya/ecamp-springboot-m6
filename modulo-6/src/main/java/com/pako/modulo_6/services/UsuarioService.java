package com.pako.modulo_6.services;

import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

  // Lista mutable de usuarios
  private List<Usuario> usuarios = new ArrayList<>(List.of(
          new Usuario(1, "Marcelos Salas", "marcelo.salas@example.com", 72),
          new Usuario(2, "Michael Perez Yackson", "michael.yackson@example.com", 16),
          new Usuario(3, "Taylor Switch", "taylor.switch@example.com", 34)
  ));

  // Obtener todos los usuarios como DTO
  public List<UsuarioDTO> getAllUsuario() {
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEdad())) // Incluye edad
            .collect(Collectors.toList());
  }

  // Obtener un usuario por ID como DTO
  public UsuarioDTO getUsuarioById(int id) {
    return usuarios.stream()
            .filter(usuario -> usuario.getId() == id)
            .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEdad())) // Incluye edad
            .findFirst()
            .orElse(new UsuarioDTO(-1, "Usuario no encontrado", 0)); // Edad por defecto en caso de error
  }
}
