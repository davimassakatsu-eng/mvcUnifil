package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Categoria;
import com.biblioteca.biblioteca.repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

@GetMapping
public String listar(Model model) {
    model.addAttribute("categorias", categoriaRepository.findAll());
    return "categoria/lista";
}

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Categoria categoria) {
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
        return "redirect:/categorias";
    }
}