package com.Biblioteca.repository;

import com.Biblioteca.app.variables.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
}
