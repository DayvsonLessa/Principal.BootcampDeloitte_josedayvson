package com.deloitte.controller;

import model.Administrador;
import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("lista", service.listar());
        return "usuarios";
    }

    @GetMapping("/usuarios/novo")
    public String formularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/usuarios/salvar")
    public String salvarUsuario(@RequestParam(required = false) Long id,
                                @RequestParam String nome,
                                @RequestParam String email,
                                @RequestParam(required = false) String setor,
                                @RequestParam(defaultValue = "false") boolean ehAdmin,
                                RedirectAttributes attributes) {
        try {
            if (id != null && id > 0) {
                // Lógica de edição (que já está fluindo)
                service.atualizarUsuario(id, nome, email);
                attributes.addFlashAttribute("mensagemSucesso", "Atualizado com sucesso!");
            } else {
                // Lógica de novo cadastro
                if (ehAdmin) {
                    service.salvarAdmin(new Administrador(null, nome, email, setor));
                } else {
                    service.criarUsuario(nome, email);
                }
                attributes.addFlashAttribute("mensagemSucesso", "Cadastrado com sucesso!");
            }
            return "redirect:/usuarios";

        } catch (RuntimeException e) {
            // AQUI É O SEGREDO: Pegamos a mensagem de erro do Service
            // e enviamos para a tela de NOVO CADASTRO
            attributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/usuarios/novo";
        }
    }
    @GetMapping("/usuarios/editar/{id}")
    public String prepararEdicao(@PathVariable Long id, Model model) {
        Usuario usuarioParaEditar = service.buscarUsuario(id);
        model.addAttribute("usuario", usuarioParaEditar);
        return "cadastro";
    }

    @GetMapping("/usuarios/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluirUsuario(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/buscar")
    public String buscarPorId(@RequestParam Long id, RedirectAttributes attributes) {
        Usuario encontrado = service.buscarUsuario(id);
        if (encontrado != null) {
            return "redirect:/usuarios/editar/" + id;
        } else {
            attributes.addFlashAttribute("mensagemErro", "Usuário com ID " + id + " não encontrado.");
            return "redirect:/usuarios";
        }
    }

    @GetMapping("/sair")
    public String sair(RedirectAttributes attributes) {
        attributes.addFlashAttribute("mensagemSucesso", "Você saiu com sucesso!");
        return "redirect:/usuarios";
    }
}