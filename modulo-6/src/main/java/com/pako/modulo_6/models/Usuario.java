package com.pako.modulo_6.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
  private int id;
  private String nombre;
  private String email;
  private int edad;
}
