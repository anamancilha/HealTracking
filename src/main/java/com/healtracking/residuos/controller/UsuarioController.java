package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.Usuario;
import com.healtracking.residuos.repository.GeradorRepository;
import com.healtracking.residuos.repository.TransportadorRepository;
import com.healtracking.residuos.repository.TratamentoRepository;
import com.healtracking.residuos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GeradorRepository geradorRepository;

    @Autowired
    private TransportadorRepository transportadorRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "lista-usuarios";
    }

    @GetMapping("/usuarios/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("geradores", geradorRepository.findAll());
        model.addAttribute("transportadores", transportadorRepository.findAll());
        model.addAttribute("tratamentos", tratamentoRepository.findAll());
        return "cadastro-usuario";
    }

    @PostMapping("/usuarios/salvar")
    public String salvarUsuario(Usuario usuario) {
        // Criptografa a senha antes de mandar para o MySQL
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }
}
