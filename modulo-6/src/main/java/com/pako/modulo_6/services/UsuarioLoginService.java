package com.pako.modulo_6.services;

import com.pako.modulo_6.models.UsuarioLogin;
import com.pako.modulo_6.repositorios.UsuarioLoginRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioLoginService implements UserDetailsService {

  @Autowired
  private UsuarioLoginRepositoryJPA usuarioLoginRepository;

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
}