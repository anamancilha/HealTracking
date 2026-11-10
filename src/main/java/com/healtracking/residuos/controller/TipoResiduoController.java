package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.TipoResiduo;
import com.healtracking.residuos.repository.TipoResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/tipos/editar/{id}")
    public String editarTipo(@PathVariable("id") Long id, Model model) {
        TipoResiduo tipoResiduo = tipoResiduoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("tipoResiduo", tipoResiduo);
        return "cadastro-tipo"; // Reaproveita a tela de cadastro para editar
    }

    @GetMapping("/tipos/excluir/{id}")
    public String excluirTipo(@PathVariable("id") Long id) {
        TipoResiduo tipoResiduo = tipoResiduoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        tipoResiduoRepository.delete(tipoResiduo);
        return "redirect:/tipos";
    }
}
