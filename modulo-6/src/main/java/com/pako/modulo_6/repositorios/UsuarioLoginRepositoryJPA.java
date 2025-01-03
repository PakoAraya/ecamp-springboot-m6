package com.pako.modulo_6.repositorios;

import com.pako.modulo_6.models.UsuarioLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioLoginRepositoryJPA extends JpaRepository<UsuarioLogin, Integer> {
    Optional<UsuarioLogin> findByUsername(String username);
}
