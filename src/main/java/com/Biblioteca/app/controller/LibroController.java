package com.Biblioteca.app.controller;

import com.Biblioteca.app.variables.Libro;

import com.Biblioteca.repository.LibroRepositorio;
import com.Biblioteca.repository.AdministradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @GetMapping("/listar")
    public String listarLibros(Model model) {
        List<Libro> libros = libroRepositorio.findAll();
        model.addAttribute("libros", libros);
        return "libro/listar";
    }

    @GetMapping("/solicitar/{id}")
    public String mostrarFormularioSolicitud(@PathVariable Integer id, Model model) {
        Libro libro = libroRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de libro no válido:" + id));
        model.addAttribute("libro", libro);
        return "libro/solicitar";
    }

    @PostMapping("/solicitar/{id}")
    public String solicitarLibro(@PathVariable Integer id) {

        return "redirect:/libro/listar";
    }


    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("administradores", administradorRepositorio.findAll());
        return "libro/crear";
    }

    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute Libro libro) {
        libroRepositorio.save(libro);
        return "redirect:/libro/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Libro libro = libroRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de libro no válido:" + id));
        model.addAttribute("libro", libro);
        model.addAttribute("administradores", administradorRepositorio.findAll());
        return "libro/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarLibro(@PathVariable Integer id, @ModelAttribute Libro libro) {
        libro.setId(id);
        libroRepositorio.save(libro);
        return "redirect:/libro/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Integer id) {
        Libro libro = libroRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de libro no válido:" + id));
        libroRepositorio.delete(libro);
        return "redirect:/libro/listar";
    }
}
