package com.pako.modulo_6.repositorios;

import com.pako.modulo_6.models.Categoria;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepositoryJPA extends JpaRepository<Categoria, Integer> {

  /*
  //Aqui tambien usamos EntityGraph para poder optimizar las preguntas con JOIN en JPA
  @EntityGraph(attributePaths = {"productoList"})
  @Override
  List<Categoria> findAll();
  */
}
