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
    return new UsuarioDTO(id, "Usuario de ejemplo", "email@dominio.com", 25);
  }

  /* Estos metodos se reemplazan por la nueva forma de trabajo con JDBC

  private final List<Usuario> usuarios = new ArrayList<>(List.of(
          new Usuario(1, "Marcelos Salas", "marcelo.salas@example.com", 72),
          new Usuario(2, "Michael Perez Yackson", "michael.yackson@example.com", 16),
          new Usuario(3, "Taylor Switch", "taylor.switch@example.com", 34)
  ));

  //Obtener todos los usuarios como DTO
  @Override
  public List<UsuarioDTO> getAllUsuario() {
    return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEdad()))
            .collect(Collectors.toList());
  }

  // Obtener un usuario por ID
  public UsuarioDTO getUsuarioById(int id) {
    return usuarios.stream()
            .filter(usuario -> usuario.getId() == id)
            .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEdad()))
            .findFirst()
            .orElse(new UsuarioDTO(-1, "Usuario no encontrado", 0)); // Usuario no encontrado
  }
*/

@Override
public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) {
   //Convertimos el DTO a un objeto Usuario
  Usuario nuevoUsuario = new Usuario(usuarioDTO.getId(),
                                     usuarioDTO.getNombre(),
                                     usuarioDTO.getEmail(),
                                     usuarioDTO.getEdad());
  //Llamamos al repositorio para guardar el usuario en la base de datos
  usuarioRepository.guardarUsuario(nuevoUsuario);
  //Regresamos el DTO del usuario guardado
  return usuarioDTO;
}

  /* Este codigo cambia ahora con la implementacion del metodo de arriba que trabaja con JDBC
  // Guardar un nuevo usuario
  @Override
  public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) {
    if (validarUsuario(usuarioDTO)) {
      int nuevoId = usuarios.size() + 1;
      Usuario nuevoUsuario = new Usuario(nuevoId, usuarioDTO.getNombre(), usuarioDTO.getEmail(), usuarioDTO.getEdad());
      usuarios.add(nuevoUsuario);
      // Devolver un UsuarioDTO con los datos del nuevo usuario
      return new UsuarioDTO(nuevoUsuario.getId(), nuevoUsuario.getNombre(), nuevoUsuario.getEdad());
    }
    return null; // Devuelve null si no se puede guardar el usuario
  }

   */

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



}
