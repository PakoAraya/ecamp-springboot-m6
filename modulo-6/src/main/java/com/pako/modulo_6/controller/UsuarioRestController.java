package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.interfaces.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // => Esto indica que es un controlador para una API REST
@RequestMapping("/api/usuarios") // => Ruta base para las APIs REST que trabajaremos
public class UsuarioRestController {

  private final UsuarioService usuarioService;

  public UsuarioRestController(UsuarioService usuarioService) {

    this.usuarioService = usuarioService;
  }

  /*
    INFORMACION IMPORTANTE EN PASO DE PARAMETROS
    No Confundir @RequestParam y @PathVariable:

    Usa @RequestParam para parámetros tipo query (?key=value).
    Usa @PathVariable para parámetros en la ruta (/path/{variable}).
  */

  // Mostrar lista de usuarios
  @GetMapping("/lista")  // => Me trae todos los usuarios registrados de BD
  public List<UsuarioDTO> obtenerUsuarios() {
    List<UsuarioDTO> usuarioLista = usuarioService.getAllUsuario();
    return usuarioLista; //
  }

  //Mostrar lista de usuarios activos en el sistema
  @GetMapping("/activos")
  public List<UsuarioDTO> usuarioActivos() {
    return usuarioService.obtenerUsuariosActivos();
  }

  //Mostrar lista de usuario que sean mayores a una determinada edad
  @GetMapping("/mayores-de/{edad}")
  public List<UsuarioDTO> mayoresDe(@PathVariable int edad) {
    return usuarioService.obtenerUsuariosMayoresDe(edad);
  }

  //Mostrar lista de usuarios que sean mayores de edad (18)
  @GetMapping("/mayores-de-edad")
  public List<UsuarioDTO> mayoresDeEdad() {
    return usuarioService.obtenerUsuariosMayoresDeEdad();
  }

  //Mostrar usuarios por un rango de edad
  @GetMapping("/rango-edades")
  public List<UsuarioDTO> obtenerUsuariosPorRangoDeEdad(
          @RequestParam int edadMin,
          @RequestParam int edadMax) {
  // Llama al servicio con los parámetros obtenidos desde la URL
    return usuarioService.findByEdadBetween(edadMin,edadMax);
  }

  //Mostrar usuario en base a correo electronico
  @GetMapping("/buscar-por-email")
  public List<UsuarioDTO> buscarPorEmail(@RequestParam String email) {
    return usuarioService.obtenerUsuariosPorCorreo(email);
  }

  //Mostrar la cantidad de usuarios activos en la App
  @GetMapping("/conteo-activos")
  public int conteoUsuariosActivos() {
    return usuarioService.contarUsuariosActivos();
  }

  //Mostrar usuario en base a una palabra clave dentro de direccion de email
  @GetMapping("/buscar-por-email-keyword")
  public List<UsuarioDTO> buscarPorEmailKeyword(@RequestParam String keyword) {
    return usuarioService.obtenerUsuarioPorKeywordCorreo(keyword);
  }
}
