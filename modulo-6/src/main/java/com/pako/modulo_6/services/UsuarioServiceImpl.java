package com.pako.modulo_6.services;

import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.interfaces.UsuarioService;
import com.pako.modulo_6.models.Usuario;
import com.pako.modulo_6.repositorios.UsuarioRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl  implements UsuarioService {

  @Autowired
  UsuarioRepositoryJPA usuarioRepositoryJPA;

/* METODO ANTES DE APLICAR EXCEPCIONES (TRY-CATCH)
  @Override
  public List<UsuarioDTO> getAllUsuario(){
    return this.usuarioRepositoryJPA.findAll().stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  Si bien este bloque de codigo funciona y activa ciertas excepciones, funciona de manera muy generica.
  @Override
  public List<UsuarioDTO> getAllUsuario(){

    try{
      return this.usuarioRepositoryJPA.findAll().stream()
              .map(usuario -> new UsuarioDTO(usuario))
              .collect(Collectors.toList());
    } catch (Exception e) {
      throw new RuntimeException("Error al obtener la lista de usuarios", e);
    }
  }

  La forma de trabajarlo correctamente se demuestra abajo, en donde es mucho mejor por trabajar
  excepciones especificas. En el metodo anterior solo mostrata si hay fallos en el JPA, no valida
  si la lista viene vacia.

  En este metodo de mas abajo tiene validaciones especificas.
  1.- Verifica si la lista viene vacia, y si esta vacia dispara excepcion.
  2.- Se manejan mas errores por la transformacion de datos
  3.- Mayor control sobre el retorno
*/
  //Metodo con manejo de excepciones
  @Override
  public List<UsuarioDTO> getAllUsuario(){

    try{
      //Intentamos obtener todos los usuarios
      List<Usuario> usuarios = usuarioRepositoryJPA.findAll();

      //Validamos que la lista no venga vacia
      if(usuarios.isEmpty()){
        throw new RuntimeException("La lista esta vacia, no se encontraron usuarios en la base de datos");
      }
      //Ahora en caso contrario, devolver los usuarios como lista DTO
      return usuarios.stream()
              .map(u -> new UsuarioDTO(u))
              .collect(Collectors.toList());

    } catch (Exception e) {
      throw new RuntimeException("Error al obtener la lista de usuarios ", e);
    }
  }

  //Se agrega bloque try-catch para evitar errores al no encontrar usuario por ID en ejecucion
  @Override
  public UsuarioDTO getUsuarioById(int id) {
    try{
      return usuarioRepositoryJPA.findById(id)
              .map(UsuarioDTO::new)
              .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    } catch (Exception e) {
      throw new RuntimeException("Error al obtener el usuario con ID: " + id, e);
    }
  }


  @Override
public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) {
   //Convertimos el DTO a un objeto Usuario
  Usuario nuevoUsuario = new Usuario(usuarioDTO.getId(),
                                     usuarioDTO.getNombre(),
                                     usuarioDTO.getEmail(),
                                     usuarioDTO.getEdad(),
                                     usuarioDTO.isActivo());
  //Llamamos al repositorio para guardar el usuario en la base de datos
  usuarioRepositoryJPA.save(nuevoUsuario);
  //Regresamos el DTO del usuario guardado
  return usuarioDTO;
}
  //Metodo para validar datos de ingreso segun pdf
  @Override
  public boolean validarUsuario(UsuarioDTO usuarioDTO) {
    if(usuarioDTO.getNombre() == null || usuarioDTO.getNombre().isEmpty()){
      return false;
    }
    if(usuarioDTO.getEmail() == null || !usuarioDTO.getEmail().contains("@")){
      return false;
    }
    if(usuarioDTO.getEdad() <= 0){
      return false;
    }
    //Si todas las condiciones son correctas, el usuario es valido
    return true;
  }

  @Override
  public List<UsuarioDTO> buscarUsuarioPorEdad() {
    List<Usuario> usuarios = usuarioRepositoryJPA.findAllByOrderByEdadAsc();
    //Mapear los usuarios obtenidos a una lista DTO
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  @Override
  public List<UsuarioDTO> traerUsuarioBootcampCl() {
    List<Usuario> usuarios = usuarioRepositoryJPA.findByEmailEndingWith("@bootcamp.cl");
    // Mapear los usuarios obtenidos a una lista de DTO
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  //Metodo para obtener a los usuarios activos (Boolean = True) en el sistema
  public List<UsuarioDTO> obtenerUsuariosActivos(){
    List<Usuario> usuarios = usuarioRepositoryJPA.findUsuariosActivos();
    //Mapear los usuarios obtenidos para pasarlos a una lista DTO
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  //Metodo para obtener usuarios mayores a cierta edad
  public List<UsuarioDTO> obtenerUsuariosMayoresDe(int edad){
    List<Usuario> usuarios = usuarioRepositoryJPA.findUsuariosMayoresDe(edad);
    //Mapear los usuarios obtenidos para pasarlos a una lista DTO
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  //Metodo para obtener a los usuarios mayores de edad
  public List<UsuarioDTO> obtenerUsuariosMayoresDeEdad(){
    List<Usuario> usuarios = usuarioRepositoryJPA.findUsuariosMayoresDeEdad();
    //Mapear los usuarios obtenidos para pasarlos a una lista DTO
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  //Metodo para mostrar un usuario en base a su correo electronico
  public List<UsuarioDTO> obtenerUsuariosPorCorreo(String email){
    List<Usuario> usuarios = usuarioRepositoryJPA.findUsuarioByEmail(email);
    //Mapear los usuarios obtenidos para pasarlos a lista DTO
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }
  //Metodo para mostrar usuarios dentro de un rango de edad en RestController
  public List<UsuarioDTO> findByEdadBetween(int edadMin, int edadMax) {
    // Llamamos al repositorio para tener la lista de usuario en rango de edad
    List<Usuario> usuarios = usuarioRepositoryJPA.findByEdadBetween(edadMin, edadMax);
    //Hay que generar un return de los usuarios en una lista UsuarioDTO
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  //Metodo para contar la cantidad de usuarios activos dentro de la App
  public int contarUsuariosActivos(){
    return usuarioRepositoryJPA.findContarUsuariosActivos();
  }

  //Metodo para mostrar usuarios en base a palabra clave dentro de direccion de correo
  public List<UsuarioDTO> obtenerUsuarioPorKeywordCorreo(String keyword){
    List<Usuario> usuarios = usuarioRepositoryJPA.findUsuarioByEmailContaining(keyword);
    //Hay que mapear la lista para pasar a una lista DTO
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  //Metodo para cambiar el estado de usuario a activo
  public void cambiarEstadoUsuario(int id, boolean estado){
    // Obtener el usuario actual desde la base de datos
    Usuario usuario = usuarioRepositoryJPA.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));

    // Cambiar el estado activo
    usuario.setActivo(!usuario.isActivo());

    // Guardar el usuario actualizado
    usuarioRepositoryJPA.save(usuario);
  }
}
