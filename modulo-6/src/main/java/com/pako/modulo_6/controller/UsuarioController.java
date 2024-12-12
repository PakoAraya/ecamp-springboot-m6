package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class UsuarioController {

  private final UsuarioService usuarioService;

  // Inyección de dependencias a través del constructor
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  /* 09-12-2024
  // Endpoint para obtener la lista de usuarios
  @GetMapping
  public List<UsuarioDTO> obtenerUsuarioList() {
    return usuarioService.getAllUsuario();
  }

   */
  //Endpoint 10-12-2024
  @GetMapping("/usuarios")
  public String obtenerUsuarios(Model model) {
    //Obtener la lista de usuarios y pasarla al modelo
    List<UsuarioDTO> usuarioLista = usuarioService.getAllUsuario();
    model.addAttribute("usuarios", usuarioLista);
    return "usuarios"; //Nombre de la vista Thymeleaf => usuarios.html
  }

  /* 09-12-2024
  // Endpoint para obtener un usuario por ID
  @GetMapping("/{id}")
  public UsuarioDTO obtenerUsuarioPorId(@PathVariable int id) {
    return usuarioService.getUsuarioById(id);
  }
  */

  //Endpoint 10-12-2024
  @GetMapping("/usuarios/{id}")
  public String obtenerUsuarioPorId(@PathVariable int id, Model model) {
    //Buscar el usuario por ID
    UsuarioDTO usuarioDTO = usuarioService.getUsuarioById(id);
    model.addAttribute("usuario", usuarioDTO);
    return "detalle-usuario"; //Nombre de la vista Thymeleaf => detalle-usuario.html
  }
}
