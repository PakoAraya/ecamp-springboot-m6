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

  //Tengo que obtener los datos de mi repository (Semejante a DAO)
  @Override
  public List<UsuarioDTO> getAllUsuario(){
    return this.usuarioRepositoryJPA.findAll().stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  @Override
  public UsuarioDTO getUsuarioById(int id) {
    // Usamos el método findById del repositorio JPA
    return usuarioRepositoryJPA.findById(id)
            .map(UsuarioDTO::new) // Convertimos el Usuario a UsuarioDTO si está presente
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id)); // Lanza una excepción si no se encuentra
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

  //Metodo para mostrar usuarios dentro de un rango de edad en RestController
  public List<UsuarioDTO> findByEdadBetween(int edadMin, int edadMax) {
    // Llamamos al repositorio para tener la lista de usuario en rango de edad
    List<Usuario> usuarios = usuarioRepositoryJPA.findByEdadBetween(edadMin, edadMax);
    //Hay que generar un return de los usuarios en una lista UsuarioDTO
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  //Metodo para mostrar un usuario en base a su correo electronico
  public List<UsuarioDTO> obtenerUsuariosPorCorreo(String email){
    List<Usuario> usuarios = usuarioRepositoryJPA.findUsuarioByEmail(email);
    //Mapear los usuarios obtenidos para pasarlos a lista DTO
    return usuarios.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
  }

}
