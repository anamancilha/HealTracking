package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.Coleta;
import com.healtracking.residuos.model.Usuario;
import com.healtracking.residuos.repository.ColetaRepository;
import com.healtracking.residuos.repository.ResiduoRepository;
import com.healtracking.residuos.repository.TransportadorRepository;
import com.healtracking.residuos.repository.TratamentoRepository;
import com.healtracking.residuos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/coletas")
public class ColetaController {

    @Autowired
    private ColetaRepository coletaRepository;

    @Autowired
    private ResiduoRepository residuoRepository;

    @Autowired
    private TransportadorRepository transportadorRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar: Admin vê todas, utilizador comum vê apenas as suas
    @GetMapping
    public String listarColetas(Model model, Principal principal) {
        String emailLogado = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElse(null);

        if (usuario != null && "ADMIN".equalsIgnoreCase(usuario.getPerfil())) {
            model.addAttribute("coletas", coletaRepository.findAll());
        } else if (usuario != null) {
            model.addAttribute("coletas", coletaRepository.findByUsuario(usuario));
        }
        return "lista-coletas";
    }

    // Abrir o formulário de cadastro passando as listas para os selects
    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("coleta", new Coleta());
        model.addAttribute("residuos", residuoRepository.findAll());
        model.addAttribute("transportadores", transportadorRepository.findAll());
        model.addAttribute("tratamentos", tratamentoRepository.findAll());
        return "cadastro-coleta";
    }

    // Salvar associando automaticamente ao utilizador logado
    @PostMapping("/salvar")
    public String salvarColeta(Coleta coleta, Principal principal) {
        String emailLogado = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElse(null);

        if (usuario != null) {
            coleta.setUsuario(usuario);
        }

        coletaRepository.save(coleta);
        return "redirect:/coletas";
    }

    // Formulário de edição
    @GetMapping("/editar/{id}")
    public String editarColeta(@PathVariable("id") Long id, Model model) {
        Coleta coleta = coletaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("coleta", coleta);
        model.addAttribute("residuos", residuoRepository.findAll());
        model.addAttribute("transportadores", transportadorRepository.findAll());
        model.addAttribute("tratamentos", tratamentoRepository.findAll());
        return "cadastro-coleta";
    }

    // Excluir coleta
    @GetMapping("/excluir/{id}")
    public String excluirColeta(@PathVariable("id") Long id) {
        coletaRepository.deleteById(id);
        return "redirect:/coletas";
    }
}