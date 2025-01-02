package com.pako.modulo_6.services;

import com.pako.modulo_6.dtos.CategoriaDTO;
import com.pako.modulo_6.interfaces.CategoriaService;
import com.pako.modulo_6.repositorios.CategoriaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

  @Autowired
  private CategoriaRepositoryJPA categoriaRepositoryJPA;

  @Override
  public List<CategoriaDTO> obtenerCategorias() {
    return this.categoriaRepositoryJPA.findAll()
            .stream()
            .map(categoria -> new CategoriaDTO(categoria, true))
            .collect(Collectors.toList());
  }
}
