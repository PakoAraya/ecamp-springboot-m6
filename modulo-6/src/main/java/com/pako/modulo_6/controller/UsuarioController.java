package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.services.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  // Inyección de dependencias a través del constructor
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  // Endpoint para obtener la lista de usuarios
  @GetMapping
  public List<UsuarioDTO> obtenerUsuarioList() {
    return usuarioService.getAllUsuario();
  }

  // Endpoint para obtener un usuario por ID
  @GetMapping("/{id}")
  public UsuarioDTO obtenerUsuarioPorId(@PathVariable int id) {
    return usuarioService.getUsuarioById(id);
  }
}
