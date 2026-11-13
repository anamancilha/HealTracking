package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.Transportador;
import com.healtracking.residuos.model.Usuario;
import com.healtracking.residuos.repository.TransportadorRepository;
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
@RequestMapping("/transportadores")
public class TransportadorController {

    @Autowired
    private TransportadorRepository transportadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listarTransportadores(Model model, Principal principal) {
        String emailLogado = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElse(null);

        if (usuario != null && "ADMIN".equalsIgnoreCase(usuario.getPerfil())) {
            model.addAttribute("transportadores", transportadorRepository.findAll());
        } else if (usuario != null) {
            model.addAttribute("transportadores", transportadorRepository.findByUsuario(usuario));
        }
        return "lista-transportadores";
    }

    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("transportador", new Transportador());
        return "cadastro-transportador";
    }

    @PostMapping("/salvar")
    public String salvarTransportador(Transportador transportador, Principal principal, Model model) {
        try {
            String emailLogado = principal.getName();
            Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElse(null);

            if (usuario != null) {
                transportador.setUsuario(usuario);
            }

            transportadorRepository.save(transportador);
            return "redirect:/transportadores";

        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            // Envia uma mensagem de erro para o HTML se o CNPJ já existir
            model.addAttribute("errorMessage", "Erro: Já existe um transportador cadastrado com este CNPJ!");
            model.addAttribute("transportador", transportador);
            return "cadastro-transportador";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarTransportador(@PathVariable("id") Long id, Model model) {
        Transportador transportador = transportadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("transportador", transportador);
        return "cadastro-transportador";
    }

    @GetMapping("/excluir/{id}")
    public String excluirTransportador(@PathVariable("id") Long id) {
        transportadorRepository.deleteById(id);
        return "redirect:/transportadores";
    }
}