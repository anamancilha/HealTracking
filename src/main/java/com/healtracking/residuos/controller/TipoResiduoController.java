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

    // 1. Lista os tipos já cadastrados
    @GetMapping("/tipos")
    public String listarTipos(Model model) {
        model.addAttribute("tipos", tipoResiduoRepository.findAll());
        return "lista-tipos";
    }

    // 2. Abre a tela com o formulário de cadastro
    @GetMapping("/tipos/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("tipoResiduo", new TipoResiduo());
        return "cadastro-tipo"; // Vai procurar por cadastro-tipo.html
    }

    // 3. Recebe os dados do formulário e salva no MySQL
    @PostMapping("/tipos/salvar")
    public String salvarTipo(TipoResiduo tipoResiduo) {
        tipoResiduoRepository.save(tipoResiduo);
        return "redirect:/tipos"; // Após salvar, volta para a lista
    }
}
