package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.interfaces.UsuarioService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class UsuarioRestController {

  private final UsuarioService usuarioService;

  public UsuarioRestController(UsuarioService usuarioService) {

    this.usuarioService = usuarioService;
  }

  // Mostrar lista de usuarios
  @GetMapping("/lista")
  public List<UsuarioDTO> obtenerUsuarios() {
    List<UsuarioDTO> usuarioLista = usuarioService.getAllUsuario();
    return usuarioLista; //
  }

}
