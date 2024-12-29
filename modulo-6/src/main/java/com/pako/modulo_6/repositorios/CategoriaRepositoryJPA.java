package com.pako.modulo_6.repositorios;

import com.pako.modulo_6.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositoryJPA extends JpaRepository<Categoria, Integer> {

}
