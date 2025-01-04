package com.pako.modulo_6.services;

import com.pako.modulo_6.dtos.UsuarioLoginDTO;
import com.pako.modulo_6.models.UsuarioLogin;
import com.pako.modulo_6.repositorios.UsuarioLoginRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioLoginService implements UserDetailsService {

  @Autowired
  private UsuarioLoginRepositoryJPA usuarioLoginRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * metodo que junta la informacion necesario para que
   * Spring Security pueda generar autenticacion
   * @param username
   * @return
   * @throws UsernameNotFoundException
   */

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //ir a buscar al usuario
    UsuarioLogin usuarioLogin = this.usuarioLoginRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado en login"));

    //se le asigna un rol al usuario de acuerdo a las credenciales de la base de datos si es que tiene esa data
    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuarioLogin.getRole());

    //return
    return new User(usuarioLogin.getUsername(), usuarioLogin.getPassword(), Collections.singletonList(authority));
  }

  public UsuarioLoginDTO addUser(UsuarioLoginDTO usuarioLoginDTO) throws Exception {
    //valida si el usuario ya existe
    if (this.usuarioLoginRepository.findByUsername(usuarioLoginDTO.getUsername()).isPresent()){
      throw  new Exception("Usuario ya existe ");
    }
    //encriptar contraseña
    String encodedPassword=passwordEncoder.encode(usuarioLoginDTO.getPassword());

    UsuarioLogin nuevoUsuario= new UsuarioLogin();

    nuevoUsuario.setUsername(usuarioLoginDTO.getUsername());
    nuevoUsuario.setPassword(encodedPassword);
    nuevoUsuario.setRole(usuarioLoginDTO.getRole());
    this.usuarioLoginRepository.save(nuevoUsuario);

    UsuarioLoginDTO  nuevoUsuarioDTO= new UsuarioLoginDTO();
    nuevoUsuarioDTO.setUsername(nuevoUsuarioDTO.getUsername());
    nuevoUsuarioDTO.setRole(nuevoUsuarioDTO.getRole());
    return  nuevoUsuarioDTO;
  }

}