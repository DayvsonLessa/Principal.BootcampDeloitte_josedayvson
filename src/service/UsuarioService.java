package service;

import model.Administrador;
import model.Usuario;
import repository.UsuarioRepository;
import java.util.List;

public class UsuarioService {
    private UsuarioRepository repository = new UsuarioRepository();

    public void criarUsuario(String nome, String email) {
        repository.salvar(new Usuario(null, nome, email));
    }

    public List<Usuario> listar() {
        return repository.listarTodos();
    }

    public void atualizarUsuario(Long id, String nome, String email) {
        repository.atualizar(new Usuario(id, nome, email));
    }

    public void excluirUsuario(Long id) {
        repository.remover(id);
    }

    public Usuario buscarUsuario(Long id) {
        return repository.buscarPorId(id);
    }

    public void criarAdmin(String nome, String email, String setor) {
        // Criei um Administrador, mas o repositório o trata como Usuario (Polimorfismo)
        Administrador adm = new Administrador(null, nome, email, setor);
        repository.salvar(adm);
    }

}

