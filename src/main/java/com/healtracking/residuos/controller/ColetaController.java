package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.Coleta;
import com.healtracking.residuos.model.Residuo;
import com.healtracking.residuos.repository.ColetaRepository;
import com.healtracking.residuos.repository.ResiduoRepository;
import com.healtracking.residuos.repository.TransportadorRepository;
import com.healtracking.residuos.repository.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ColetaController { 

    @Autowired
    private ColetaRepository coletaRepository; 

    @Autowired
    private ResiduoRepository residuoRepository;

    @Autowired
    private TransportadorRepository transportadorRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @GetMapping("/coletas")
    public String listarColetas(Model model) {
        model.addAttribute("coletas", coletaRepository.findAll());
        return "lista-coletas";
    }

    @GetMapping("/coletas/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("coleta", new Coleta());
        model.addAttribute("transportadores", transportadorRepository.findAll());
        model.addAttribute("tratamentos", tratamentoRepository.findAll());
        
        // Regra de Rastreabilidade: Filtrar apenas resíduos ainda armazenados nos geradores
        List<Residuo> residuosDisponiveis = residuoRepository.findAll().stream()
                .filter(r -> "Armazenado".equals(r.getStatus()))
                .collect(Collectors.toList());
        model.addAttribute("residuos", residuosDisponiveis);
        
        return "cadastro-coleta";
    }

    @PostMapping("/coletas/salvar")
    public String salvarColeta(Coleta coleta) {
        // Atualiza o status do resíduo vinculado para "Coletado" antes de salvar a movimentação
        Residuo residuo = coleta.getResiduo();
        if (residuo != null) {
            residuo.setStatus("Coletado");
            residuoRepository.save(residuo);
        }

        coletaRepository.save(coleta);
        return "redirect:/coletas";
    }
}
