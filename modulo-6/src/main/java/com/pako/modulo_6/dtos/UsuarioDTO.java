
package com.pako.modulo_6.dtos;

public class UsuarioDTO {
  private int id;
  private String nombre;
  private int edad; // Incluye el campo edad

  public UsuarioDTO(int id, String nombre, int edad) {
    this.id = id;
    this.nombre = nombre;
    this.edad = edad; // Asegúrate de asignar este valor
  }

  public UsuarioDTO() {
  }

  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public int getEdad() {
    return edad; // Getter para edad
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setEdad(int edad) {
    this.edad = edad; // Setter para edad
  }

  @Override
  public String toString() {
    return "UsuarioDTO{id=" + id + ", nombre='" + nombre + "', edad=" + edad + "}";
  }
}
