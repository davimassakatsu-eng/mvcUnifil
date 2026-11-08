package com.biblioteca.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblioteca.biblioteca.model.Livro;
import com.biblioteca.biblioteca.repository.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository livroRepository;
    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
    @GetMapping("/livros")
    public String listarLivros (Model model) {

        List<Livro> livros = livroRepository.findAll();
        model.addAttribute("livros", livros);
        return "livros/lista";
    }
    
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("livro", new Livro());
        return "livro/form";
    }

        @PostMapping("/novo")
    public String cadastrarLivro(
        @ModelAttribute Livro Livro) {

        livroRepository.save(Livro);
        return "redirect:/livros";
    }

    @GetMapping("/livros/excluir/{indice}")
    public String excluir(@PathVariable int indice) {
        List<Livro> livros = livroRepository.findAll();
        livroRepository.delete(livros.get(indice));
        return "redirect:/livros";

    }
}

