package com.pako.modulo_6.services;

import com.pako.modulo_6.dtos.ProductoDTO;
import com.pako.modulo_6.dtos.UsuarioDTO;
import com.pako.modulo_6.interfaces.UsuarioService;
import com.pako.modulo_6.models.Producto;
import com.pako.modulo_6.models.Usuario;
import com.pako.modulo_6.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl  implements UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  //Tengo que obtener los datos de mi repository (Semejante a DAO)
  @Override
  public List<UsuarioDTO> getAllUsuario(){
    return this.usuarioRepository.getAllUsuario().stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  @Override
  public UsuarioDTO getUsuarioById(int id) {
    // Aquí debería ir la lógica para obtener el usuario por ID
    // Esto es solo un ejemplo simple, puedes adaptarlo según tu implementación
    return new UsuarioDTO(id, "Usuario de ejemplo", "email@dominio.com", 25, true);
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
  usuarioRepository.guardarUsuario(nuevoUsuario);
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
    List<Usuario> usuarios = usuarioRepository.buscarUsuarioPorEdad();
    //Mapear los usuarios obtenidos a una lista DTO
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }

  @Override
  public List<UsuarioDTO> traerUsuarioBootcampCl() {
    List<Usuario> usuarios = usuarioRepository.traerUsuarioBootcampCl();
    // Mapear los usuarios obtenidos a una lista de DTO
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());
  }


}
