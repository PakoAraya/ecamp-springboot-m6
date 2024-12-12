package com.pako.modulo_6.dtos;


public class UsuarioDTO {
  private int id;
  private String nombre;

  public UsuarioDTO(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public UsuarioDTO() {
  }

  public int getId() {
    return this.id;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof UsuarioDTO)) return false;
    final UsuarioDTO other = (UsuarioDTO) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.getId() != other.getId()) return false;
    final Object this$nombre = this.getNombre();
    final Object other$nombre = other.getNombre();
    if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof UsuarioDTO;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.getId();
    final Object $nombre = this.getNombre();
    result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
    return result;
  }

  public String toString() {
    return "UsuarioDTO(id=" + this.getId() + ", nombre=" + this.getNombre() + ")";
  }
}
