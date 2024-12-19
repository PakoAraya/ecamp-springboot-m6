package com.pako.modulo_6.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "nombre", nullable = false, length = 100)
  private String nombre;

  @Column(name = "email", nullable = false, length = 100)
  private String email;

  @Column(name = "edad", nullable = false)
  private int edad;

  @Column(name = "activo", nullable = false)
  private boolean activo;
}
