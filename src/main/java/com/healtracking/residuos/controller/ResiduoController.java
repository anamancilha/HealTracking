package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.Residuo;
import com.healtracking.residuos.repository.GeradorRepository;
import com.healtracking.residuos.repository.ResiduoRepository;
import com.healtracking.residuos.repository.TipoResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResiduoController {

    @Autowired
    private ResiduoRepository residuoRepository;

    @Autowired
    private GeradorRepository geradorRepository;

    @Autowired
    private TipoResiduoRepository tipoResiduoRepository;

    // Listar todos os lançamentos de resíduos
    @GetMapping("/residuos")
    public String listarResiduos(Model model) {
        model.addAttribute("residuos", residuoRepository.findAll());
        return "lista-residuos";
    }

    // Abrir o formulário de cadastro passando as listas para os campos de <select>
    @GetMapping("/residuos/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("residuo", new Residuo());
        model.addAttribute("geradores", geradorRepository.findAll());
        model.addAttribute("tipos", tipoResiduoRepository.findAll());
        return "cadastro-residuo";
    }

    // Salvar e definir o status inicial automaticamente como "Armazenado"
    @PostMapping("/residuos/salvar")
    public String salvarResiduo(Residuo residuo) {
        residuo.setStatus("Armazenado"); // Todo lixo começa armazenado no hospital
        residuoRepository.save(residuo);
        return "redirect:/residuos";
    }
}
