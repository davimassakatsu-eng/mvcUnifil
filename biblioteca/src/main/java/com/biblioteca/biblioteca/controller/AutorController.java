package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Autor;
import com.biblioteca.biblioteca.repository.AutorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        return "autor/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("autor", new Autor());
        return "autor/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Autor autor) {
        autorRepository.save(autor);
        return "redirect:/autores";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        autorRepository.deleteById(id);
        return "redirect:/autores";
    }
}