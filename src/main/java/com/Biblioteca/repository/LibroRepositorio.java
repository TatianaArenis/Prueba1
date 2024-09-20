package com.Biblioteca.repository;

import com.Biblioteca.app.variables.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepositorio extends JpaRepository<Libro, Integer> {
}
