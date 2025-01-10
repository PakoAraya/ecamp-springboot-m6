package com.pako.modulo_6.controller;

import com.pako.modulo_6.dtos.UsuarioLoginDTO;
import com.pako.modulo_6.interfaces.CategoriaService;
import com.pako.modulo_6.interfaces.ProductoService;
import com.pako.modulo_6.services.UsuarioLoginService;
import com.pako.modulo_6.jwt.JwtAuthenticationFilter;
import com.pako.modulo_6.jwt.JwtTokenService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private ProductoService productoService;

  @Autowired
  private CategoriaService categoriaService;

  @Autowired
  private UsuarioLoginService usuarioLoginService;

  @Autowired
  private JwtTokenService jwtTokenService;


  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO usuarioDTO) {
    try {

      UsuarioLoginDTO usuarioAutenticado = usuarioLoginService.authenticate(usuarioDTO.getUsername(), usuarioDTO.getPassword());

      String token = jwtTokenService.generateToken(usuarioAutenticado.getUsername(), usuarioAutenticado.getRole());

      return ResponseEntity.ok(Map.of(
              "token", token,
              "username", usuarioAutenticado.getUsername(),
              "role", usuarioAutenticado.getRole()
      ));
    } catch (UsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
              "error", "Credenciales inválidas"
      ));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
              "error", "Error al procesar el login",
              "detalle", e.getMessage()
      ));
    }
  }
}