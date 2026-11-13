package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.Tratamento;
import com.healtracking.residuos.model.Usuario;
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
@RequestMapping("/tratamentos")
public class TratamentoController {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar: Admin vê tudo, utilizador comum vê apenas os seus tratamentos
    @GetMapping
    public String listarTratamentos(Model model, Principal principal) {
        String emailLogado = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElse(null);

        if (usuario != null && "ADMIN".equalsIgnoreCase(usuario.getPerfil())) {
            model.addAttribute("tratamentos", tratamentoRepository.findAll());
        } else if (usuario != null) {
            model.addAttribute("tratamentos", tratamentoRepository.findByUsuario(usuario));
        }
        return "lista-tratamentos";
    }

    // Abrir o formulário de cadastro
    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("tratamento", new Tratamento());
        return "cadastro-tratamento";
    }

    // Salvar associando ao utilizador logado
    @PostMapping("/salvar")
    public String salvarTratamento(Tratamento tratamento, Principal principal) {
        String emailLogado = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElse(null);

        if (usuario != null) {
            tratamento.setUsuario(usuario);
        }

        tratamentoRepository.save(tratamento);
        return "redirect:/tratamentos";
    }

    // Formulário de edição
    @GetMapping("/editar/{id}")
    public String editarTratamento(@PathVariable("id") Long id, Model model) {
        Tratamento tratamento = tratamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("tratamento", tratamento);
        return "cadastro-tratamento";
    }

    // Excluir tratamento
    @GetMapping("/excluir/{id}")
    public String excluirTratamento(@PathVariable("id") Long id) {
        tratamentoRepository.deleteById(id);
        return "redirect:/tratamentos";
    }
}