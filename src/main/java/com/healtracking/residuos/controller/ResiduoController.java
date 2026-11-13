package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.Residuo;
import com.healtracking.residuos.model.Usuario;
import com.healtracking.residuos.repository.GeradorRepository;
import com.healtracking.residuos.repository.ResiduoRepository;
import com.healtracking.residuos.repository.TipoResiduoRepository;
import com.healtracking.residuos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ResiduoController {

    @Autowired
    private ResiduoRepository residuoRepository;

    @Autowired
    private GeradorRepository geradorRepository;

    @Autowired
    private TipoResiduoRepository tipoResiduoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar: Admin vê tudo, utilizador comum vê apenas os seus resíduos
    @GetMapping("/residuos")
    public String listarResiduos(Model model, Principal principal) {
        String emailLogado = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElse(null);

        if (usuario != null && "ADMIN".equalsIgnoreCase(usuario.getPerfil())) {
            model.addAttribute("residuos", residuoRepository.findAll());
        } else if (usuario != null) {
            model.addAttribute("residuos", residuoRepository.findByUsuario(usuario));
        }
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

    @PostMapping("/residuos/salvar")
    public String salvarResiduo(Residuo residuo, Principal principal) {
        // Se por algum motivo a sessão caiu e o principal for nulo, manda de volta para o login
        if (principal == null) {
            return "redirect:/login";
        }

        System.out.println(">>> ENTROU NO MÉTODO SALVAR RESÍDUO COM SUCESSO! <<<");

        String emailLogado = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElse(null);

        if (usuario != null) {
            residuo.setUsuario(usuario); // Associa o autor do registo
        }

        residuo.setStatus("Armazenado");
        residuoRepository.save(residuo);
        return "redirect:/residuos";

    }

    // Abrir o formulário de edição carregando os dados do resíduo e as listas para os selects
    @GetMapping("/residuos/editar/{id}")
    public String editarResiduo(@PathVariable("id") Long id, Model model) {
        Residuo residuo = residuoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("residuo", residuo);
        model.addAttribute("geradores", geradorRepository.findAll());
        model.addAttribute("tipos", tipoResiduoRepository.findAll());
        return "cadastro-residuo";
    }

    // Excluir o resíduo do banco de dados
    @GetMapping("/residuos/excluir/{id}")
    public String excluirResiduo(@PathVariable("id") Long id) {
        residuoRepository.deleteById(id);
        return "redirect:/residuos";
    }
}