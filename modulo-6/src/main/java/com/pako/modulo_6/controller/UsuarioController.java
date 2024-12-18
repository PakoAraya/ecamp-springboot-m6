package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.interfaces.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario") // Ruta base para todos los métodos de este controlador
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  /***
   * Metodo que entrega una lista completa de usuarios
   *
   * @author Francisco Javier Araya H
   * @param model entrega un model de ui de spring
   * @return retorna nombre de template a usar
   */
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

  // Guardar un nuevo usuario usando las validaciones de spring
  @PostMapping("/guardar")
  public String guardarUsuario(
          @Valid //Habilita validaciones definidas para UsuarioDTO
          @ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO,
          BindingResult result, //Captura los errores de validacion de UsuarioDTO
          Model model
  ) {
    //Esto es para la captura de errores de UsuarioDTO
    if(result.hasErrors()){
      model.addAttribute("error", "Error en el envio de formularios");
      model.addAttribute("errores", result.getAllErrors());
      System.out.println(result.getAllErrors());
      return "formulario-usuario"; // Vista Thymeleaf
    }
    //Si no hay errores, se muestra el objeto guardado en la vista
    System.out.println(usuarioDTO);
    UsuarioDTO nuevoUsuarioDTO = usuarioService.guardarUsuario(usuarioDTO);
    System.out.println(nuevoUsuarioDTO);
    model.addAttribute("nuevo", usuarioDTO); //se usa nuevo (de nuevo usuario)
    return "nuevo-usuario"; //Vista para el nuevo usuario
  }

  //Metodo para validar usuario
  @PostMapping("/validar")
  public String validarYGuardarUsuario(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
    // Valida los datos del usuario
    if (!usuarioService.validarUsuario(usuarioDTO)) {
      model.addAttribute("error", "Datos inválidos. Por favor, verifica la información.");
      return "formulario-usuario";  // Retorna a la vista del formulario con error
    }

    // Intenta guardar el usuario y obtiene el objeto guardado
    UsuarioDTO usuarioGuardado = usuarioService.guardarUsuario(usuarioDTO);

    // Si no se guardó correctamente, muestra un mensaje de error
    if (usuarioGuardado == null) {
      model.addAttribute("error", "Error al guardar usuario.");
      return "formulario-usuario";  // Retorna a la vista del formulario con error
    }

    // Si se guardó correctamente, redirige a la lista de usuarios
    return "redirect:/usuario/lista";
  }

  // Endpoint Mostrar usuarios ordenados por edad
  @GetMapping("/lista/edad")
  public String buscarUsuarioPorEdad(Model model){
    List<UsuarioDTO> usuarioLista = usuarioService.buscarUsuarioPorEdad();
    model.addAttribute("usuarios", usuarioLista);
    return "usuarios-edad"; // => Esta vista se debe mostrar en Thymeleaf
  }

  //Endpoint mostrando usuarios cuyo correo termine en "@bootcamp.cl"
  @GetMapping("/lista/correosbootcamp")
  public String traerUsuarioBootcampCl(Model model){
    List<UsuarioDTO> usuarioLista = usuarioService.traerUsuarioBootcampCl();
    model.addAttribute("usuarios", usuarioLista);
    return "usuarios-bootcamp"; // => Esta vista se debe mostrar en thymeleaf
  }

}
