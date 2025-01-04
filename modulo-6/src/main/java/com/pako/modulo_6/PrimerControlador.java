package com.pako.modulo_6;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class PrimerControlador {


  @GetMapping("/access-denied")
  public String accessDenied() {
    return "access-denied"; // Vista para acceso denegado
  }
}