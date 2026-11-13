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
import org.springframework.web.bind.annotation.PathVariable;
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
        // Se já tiver ID (edição) e a senha vier vazia, podemos manter a senha antiga para não corromper o hash
        if (usuario.getIdUsuario() != null && (usuario.getSenha() == null || usuario.getSenha().isEmpty())) {
            Usuario usuarioAntigo = usuarioRepository.findById(usuario.getIdUsuario()).orElse(null);
            if (usuarioAntigo != null) {
                usuario.setSenha(usuarioAntigo.getSenha());
            }
        } else {
            // Caso contrário, encripta a nova senha normalmente
            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
        }

        if (usuario.getPerfil() == null || usuario.getPerfil().isEmpty()) {
            usuario.setPerfil("USER");
        }

        usuarioRepository.save(usuario);

        // Se for um novo registo público, redireciona para o login. Se for gestão de admin, pode redirecionar para /usuarios
        return "redirect:/login?sucesso";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de utilizador inválido: " + id));

        model.addAttribute("usuario", usuario);
        model.addAttribute("geradores", geradorRepository.findAll());
        model.addAttribute("transportadores", transportadorRepository.findAll());
        model.addAttribute("tratamentos", tratamentoRepository.findAll());
        return "cadastro-usuario";
    }

    @GetMapping("/usuarios/excluir/{id}")
    public String excluirUsuario(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}