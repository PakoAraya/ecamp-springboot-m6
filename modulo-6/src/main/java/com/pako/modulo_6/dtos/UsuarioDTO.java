
package com.pako.modulo_6.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
  private int id;
  @NotNull //El nombre no puede ser nulo
  @Size(min = 5, max = 80) //El nombre debe tener entre 5 y 80 caracteres
  private String nombre;

  @NotNull //Correo no puede ser nulo
  @Email //Debe proporcionar un correo validos
  private String email;

  @NotNull //La edad no puede ser un valor nulo
  @Positive //La edad debe ser un valor positivo
  private int edad;


  //Constructor de la clase UsuarioDTO con id, nombre y edad
  public UsuarioDTO(int id, String nombre, int edad) {
    this.id = id;
    this.nombre = nombre;
    this.edad = edad; // Asegúrate de asignar este valor
  }

  //Constructor vacio de la clase UsuarioDTO
  public UsuarioDTO() {
  }

  //Constructor completo de la clase
  public UsuarioDTO(int id, String nombre, String email, int edad) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.edad = edad;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  @Override
  public String toString() {
    return "UsuarioDTO{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", email='" + email + '\'' +
            ", edad=" + edad +
            '}';
  }
}
