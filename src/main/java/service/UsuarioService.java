package service;

import model.Usuario;
import model.Administrador;
import repository.UsuarioRepository;
import java.util.List;

public class UsuarioService {
    private UsuarioRepository repository = new UsuarioRepository();

    public void criarUsuario(String nome, String email) {
        Usuario novo = new Usuario(null, nome, email);
        repository.salvar(novo);
    }


    public void salvarAdmin(Administrador admin) {
        repository.salvar(admin);
    }

    public List<Usuario> listar() {
        return repository.listarTodos();
    }

    public Usuario buscarUsuario(Long id) {
        return repository.buscarPorId(id);
    }

    public void atualizarUsuario(Long id, String nome, String email) {
        Usuario u = repository.buscarPorId(id);
        if (u != null) {
            u.setNome(nome);
            u.setEmail(email);
            repository.atualizar(u);
        }
    }

    public void excluirUsuario(Long id) {
        repository.excluir(id);
    }
}

