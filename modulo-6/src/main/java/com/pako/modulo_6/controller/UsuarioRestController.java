package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.interfaces.UsuarioService;
import com.pako.modulo_6.models.Usuario;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  // => Esto indica que es un controlador para una API REST
@RequestMapping("/api/usuarios") // => Ruta base para las APIs REST que trabajaremos
public class UsuarioRestController {

  private final UsuarioService usuarioService;

  public UsuarioRestController(UsuarioService usuarioService) {

    this.usuarioService = usuarioService;
  }

  // Mostrar lista de usuarios
  @GetMapping("/lista")  // => Me trae todos los usuarios registrados de BD
  public List<UsuarioDTO> obtenerUsuarios() {
    List<UsuarioDTO> usuarioLista = usuarioService.getAllUsuario();
    return usuarioLista; //
  }

  //Mostrar usuarios por un rango de edad
  @GetMapping("/rango-edades")
  public List<UsuarioDTO> obtenerUsuariosPorRangoDeEdad(
          @RequestParam int edadMin,
          @RequestParam int edadMax) {
  // Llama al servicio con los parámetros obtenidos desde la URL
    return usuarioService.findByEdadBetween(edadMin,edadMax);
  }

}
