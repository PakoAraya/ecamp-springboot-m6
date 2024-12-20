package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.interfaces.UsuarioService;
import org.springframework.http.ResponseEntity;
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
  /*
    El siguiente metodo se debe probar en postman o insomnia, si se hace con navegador dara un
    error 405 ocurre porque los navegadores no soportan solicitudes PUT directamente desde la
    barra de direcciones.

    ResponseEntity es una clase en Spring Framework que se utiliza para representar toda la respuesta HTTP,
    incluidas las cabeceras (headers), el cuerpo (body) y el código de estado (status code).
    Es una herramienta poderosa para personalizar la respuesta de una API REST. Se encuentra en el paquete
    org.springframework.http.
    ¿Por qué usar ResponseEntity?
    Permite controlar detalles adicionales de la respuesta HTTP.
    Es ideal para crear APIs REST más flexibles y explícitas.
    Se usa cuando necesitas enviar respuestas con diferentes códigos de estado y cabeceras personalizadas.
    Por ejemplo:

    Enviar un código de estado como 201 (Created) en lugar de un simple 200 (OK).
    Incluir cabeceras como Location para redirecciones.
    Retornar un cuerpo de respuesta (como un mensaje o un objeto JSON).
   */
  //Mostrar usuario y cambiar su estado a activo y inactivo
  @PutMapping("/{id}/estado-activo")
  public ResponseEntity<String> cambiarEstadoUsuario(@PathVariable int id, @RequestParam boolean activo) {
    usuarioService.cambiarEstadoUsuario(id, activo);
    return ResponseEntity.ok("Estado del usuario con ID " + id + " actualizado correctamente");
  }
}
