package com.healtracking.residuos.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.healtracking.residuos.model.Gerador;
import com.healtracking.residuos.model.Usuario;
import com.healtracking.residuos.repository.GeradorRepository;
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
@RequestMapping("/geradores")
public class GeradorController {

    @Autowired
    private GeradorRepository geradorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar: Admin vê todos, utilizador comum vê apenas os seus
    @GetMapping
    public String listarGeradores(Model model, Principal principal) {
        String emailLogado = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElse(null);

        if (usuario != null && "ADMIN".equalsIgnoreCase(usuario.getPerfil())) {
            model.addAttribute("geradores", geradorRepository.findAll());
        } else if (usuario != null) {
            model.addAttribute("geradores", geradorRepository.findByUsuario(usuario));
        }
        return "lista-geradores";
    }

    // Abrir o formulário de novo gerador
    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("gerador", new Gerador());
        return "cadastro-gerador";
    }

    // Salvar o gerador associando ao utilizador logado
    @PostMapping("/salvar")
    public String salvarGerador(@Valid Gerador gerador, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "cadastro-gerador";
        }

        String emailLogado = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElse(null);

        if (usuario != null) {
            gerador.setUsuario(usuario);
        }

        geradorRepository.save(gerador);
        return "redirect:/geradores";
    }

    // Formulário de edição
    @GetMapping("/editar/{id}")
    public String editarGerador(@PathVariable("id") Long id, Model model) {
        Gerador gerador = geradorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("gerador", gerador);
        return "cadastro-gerador";
    }

    // Excluir gerador
    @GetMapping("/excluir/{id}")
    public String excluirGerador(@PathVariable("id") Long id) {
        geradorRepository.deleteById(id);
        return "redirect:/geradores";
    }
}