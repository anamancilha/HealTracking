package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.Transportador;
import com.healtracking.residuos.repository.TransportadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransportadorController {

    @Autowired
    private TransportadorRepository transportadorRepository;

    @GetMapping("/transportadores")
    public String listarTransportadores(Model model) {
        model.addAttribute("transportadores", transportadorRepository.findAll());
        return "lista-transportadores";
    }

    @GetMapping("/transportadores/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("transportador", new Transportador());
        return "cadastro-transportador";
    }

    @PostMapping("/transportadores/salvar")
    public String salvarTransportador(Transportador transportador) {
        transportadorRepository.save(transportador);
        return "redirect:/transportadores";
    }
}
