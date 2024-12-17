package com.pako.modulo_6.dtos;

import com.pako.modulo_6.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {

  private int id;

  @NotNull // El nombre no puede ser nulo
  @Size(min = 5, max = 80) // El nombre debe tener entre 5 y 80 caracteres
  private String nombre;

  @NotNull // El correo no puede ser nulo
  @Email // Debe proporcionar un correo válido
  private String email;

  @NotNull // La edad no puede ser un valor nulo
  @Positive // La edad debe ser un valor positivo
  private int edad;

  // Constructor vacío
  public UsuarioDTO() {
    // No hace nada, solo inicializa el objeto
  }

  // Constructor para crear un DTO a partir de un Usuario con id, nombre y edad
  public UsuarioDTO(int id, String nombre, int edad) {
    this.id = id;
    this.nombre = nombre;
    this.edad = edad;
  }

  // Constructor para crear un DTO a partir de un Usuario completo
  public UsuarioDTO(Usuario usuario) {
    this.id = usuario.getId();
    this.nombre = usuario.getNombre();
    this.email = usuario.getEmail();
    this.edad = usuario.getEdad();
  }

  // Constructor completo de la clase
  public UsuarioDTO(int id, String nombre, String email, int edad) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.edad = edad;
  }

  // Getters y setters
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
