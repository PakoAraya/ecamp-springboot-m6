package com.pako.modulo_6.dtos;

import com.pako.modulo_6.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {

  private int id;

  @NotNull(message = "El nombre no puede ser nulo.")
  @Size(min = 5, max = 80, message = "El nombre debe tener entre 5 y 80 caracteres.")
  private String nombre;

  @NotNull(message = "El email no puede ser nulo.")
  @Email(message = "Debe proporcionar un correo válido.")
  private String email;

  @NotNull(message = "La edad no puede ser nula.")
  @Positive(message = "La edad debe ser un valor positivo.")
  private int edad;

  @NotNull(message = "El estado activo no puede ser nulo.")
  private boolean activo;

  // Constructor vacío
  public UsuarioDTO() {}

  // Constructor para DTO parcial (puedes reconsiderarlo si es necesario a futuro, fue usado en ejercicios pasados)
  public UsuarioDTO(int id, String nombre, int edad) {
    this.id = id;
    this.nombre = nombre;
    this.edad = edad;
    this.email = ""; // Valor predeterminado
    this.activo = false; // Valor predeterminado
  }

  // Constructor para inicializar desde un modelo Usuario
  public UsuarioDTO(Usuario usuario) {
    this.id = usuario.getId();
    this.nombre = usuario.getNombre();
    this.email = usuario.getEmail();
    this.edad = usuario.getEdad();
    this.activo = usuario.isActivo();
  }

  // Constructor completo
  public UsuarioDTO(int id, String nombre, String email, int edad, boolean activo) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.edad = edad;
    this.activo = activo;
  }

  // Getters y Setters
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

  public boolean isActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  @Override
  public String toString() {
    return "UsuarioDTO{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", email='" + email + '\'' +
            ", edad=" + edad +
            ", activo=" + activo +
            '}';
  }
}