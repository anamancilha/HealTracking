package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.Tratamento;
import com.healtracking.residuos.repository.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TratamentoController {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @GetMapping("/tratamentos")
    public String listarTratamentos(Model model) {
        model.addAttribute("tratamentos", tratamentoRepository.findAll());
        return "lista-tratamentos";
    }

    @GetMapping("/tratamentos/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("tratamento", new Tratamento());
        return "cadastro-tratamento";
    }

    @PostMapping("/tratamentos/salvar")
    public String salvarTratamento(Tratamento tratamento) {
        tratamentoRepository.save(tratamento);
        return "redirect:/tratamentos";
    }
}
