package com.Biblioteca.app.controller;

import com.Biblioteca.app.variables.Administrador;
import com.Biblioteca.repository.AdministradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @GetMapping("/listar")
    public String listarAdministradores(Model model) {
        List<Administrador> administradores = administradorRepositorio.findAll();
        model.addAttribute("administradores", administradores);
        return "admin/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "admin/crear";
    }

    @PostMapping("/guardar")
    public String guardarAdministrador(@ModelAttribute Administrador administrador) {
        administradorRepositorio.save(administrador);
        return "redirect:/admin/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Optional<Administrador> optionalAdministrador = administradorRepositorio.findById(id);
        if (optionalAdministrador.isPresent()) {
            model.addAttribute("administrador", optionalAdministrador.get());
            return "admin/crear";
        } else {
            throw new IllegalArgumentException("ID de administrador no válido:" + id);
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarAdministrador(@PathVariable Integer id, @ModelAttribute Administrador administrador) {
        administrador.setId(id);
        administradorRepositorio.save(administrador);
        return "redirect:/admin/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAdministrador(@PathVariable Integer id) {
        administradorRepositorio.deleteById(id);
        return "redirect:/admin/listar";
    }
}
