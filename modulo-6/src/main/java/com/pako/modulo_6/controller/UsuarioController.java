package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.interfaces.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario") // Ruta base para todos los métodos de este controlador
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  // Mostrar lista de usuarios
  @GetMapping("/lista")
  public String obtenerUsuarios(Model model) {
    List<UsuarioDTO> usuarioLista = usuarioService.getAllUsuario();
    model.addAttribute("usuarios", usuarioLista);
    return "usuarios"; // Vista Thymeleaf
  }

  // Mostrar formulario para crear un nuevo usuario
  @GetMapping("/nuevo")
  public String mostrarFormulario(Model model) {
    model.addAttribute("usuarioDTO", new UsuarioDTO());
    return "formulario-usuario"; // Vista Thymeleaf
  }

  // Guardar un nuevo usuario
  @PostMapping("/guardar")
  public String guardarUsuario(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, Model model) {
    // Aquí podrías agregar lógica para guardar en la base de datos
    // Actualmente solo mostramos el nuevo usuario
    model.addAttribute("nuevo", usuarioDTO);
    return "nuevo-usuario"; // Vista Thymeleaf
  }
  //Metodo para validar usuario
  @PostMapping("/validar")
  public String validarYGuardarUsuario(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
    if (!usuarioService.validarUsuario(usuarioDTO)) {
      model.addAttribute("error", "Datos inválidos. Por favor, verifica la información.");
      return "formulario-usuario";
    }

    if (!usuarioService.guardarUsuario(usuarioDTO)) {
      model.addAttribute("error", "Error al guardar usuario.");
      return "usuarios";
    }

    return "redirect:/usuario/lista"; // Redirige a la lista si tiene éxito
  }

}
