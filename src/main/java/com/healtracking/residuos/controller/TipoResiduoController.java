package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.TipoResiduo;
import com.healtracking.residuos.repository.TipoResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TipoResiduoController {

    @Autowired
    private TipoResiduoRepository tipoResiduoRepository;

    
    @GetMapping("/tipos")
    public String listarTipos(Model model) {
        model.addAttribute("tipos", tipoResiduoRepository.findAll());
        return "lista-tipos";
    }

    
    @GetMapping("/tipos/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("tipoResiduo", new TipoResiduo());
        return "cadastro-tipo"; 
    }

    
    @PostMapping("/tipos/salvar")
    public String salvarTipo(TipoResiduo tipoResiduo) {
        tipoResiduoRepository.save(tipoResiduo);
        return "redirect:/tipos"; 
    }
}
