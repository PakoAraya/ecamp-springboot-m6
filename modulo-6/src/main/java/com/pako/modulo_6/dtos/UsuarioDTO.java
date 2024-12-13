
package com.pako.modulo_6.dtos;

public class UsuarioDTO {
  private int id;
  private String nombre;
  private String email;
  private int edad;

  public UsuarioDTO(int id, String nombre, int edad) {
    this.id = id;
    this.nombre = nombre;
    this.edad = edad; // Asegúrate de asignar este valor
  }

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
