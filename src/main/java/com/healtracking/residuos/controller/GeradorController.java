package com.healtracking.residuos.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.healtracking.residuos.model.Gerador;
import com.healtracking.residuos.repository.GeradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GeradorController {

    @Autowired
    private GeradorRepository geradorRepository;

    // Listar todos os hospitais/clínicas cadastrados
    @GetMapping("/geradores")
    public String listarGeradores(Model model) {
        model.addAttribute("geradores", geradorRepository.findAll());
        return "lista-geradores"; // Vai procurar por lista-geradores.html
    }

    // Abrir o formulário de novo gerador
    @GetMapping("/geradores/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("gerador", new Gerador());
        return "cadastro-gerador"; // Vai procurar por cadastro-gerador.html
    }

    // Salvar o gerador no MySQL e redirecionar
    @PostMapping("/geradores/salvar")
    public String salvarGerador(@Valid Gerador gerador, BindingResult result) {
        // Se houver algum erro (ex: CNPJ falso ou campo em branco), volta para o formulário
        if (result.hasErrors()) {
            return "cadastro-gerador";
        }
        
        geradorRepository.save(gerador);
        return "redirect:/geradores";
    }
}

