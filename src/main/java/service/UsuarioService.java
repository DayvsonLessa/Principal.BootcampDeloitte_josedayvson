package service;

import model.Usuario;
import model.Administrador;
import repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    /**
     * Método auxiliar para validar se o e-mail já existe no sistema.
     * Centraliza a regra de negócio para ser usada por Usuários e Admins.
     */
    private void validarEmailUnico(String email) {
        List<Usuario> todosOsUsuarios = repository.listarTodos();

        boolean emailJaCadastrado = todosOsUsuarios.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));

        if (emailJaCadastrado) {
            System.out.println("ALERTA: Tentativa de cadastro com e-mail duplicado: " + email);
            throw new RuntimeException("Erro: Este e-mail já está cadastrado no sistema!");
        }
    }

    public void criarUsuario(String nome, String email) {
        validarEmailUnico(email); // Valida antes de criar
        Usuario novo = new Usuario(null, nome, email);
        repository.salvar(novo);
    }

    public void salvarAdmin(Administrador admin) {
        validarEmailUnico(admin.getEmail()); // Agora o Admin também é validado!
        repository.salvar(admin);

    }

    public void atualizarNoRepositorio(Administrador admin) {
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
    public void salvarEdicaoDireta(Long id, String nome, String email, String setor, boolean ehAdmin) {
        Usuario u = repository.buscarPorId(id);
        if (u != null) {
            u.setNome(nome);
            u.setEmail(email);

            // Se ele for um Administrador, atualizamos o setor
            if (u instanceof Administrador) {
                ((Administrador) u).setSetor(setor);
            }

            // Se você mudou de Usuário para ADM ou vice-versa na tela,
            // e quer que isso reflita sem excluir, o objeto u precisa ser do tipo certo.
            // Como o Java não muda o tipo do objeto "no ar", o ideal é apenas atualizar os dados.

            repository.atualizar(u);
        }
    }

    public void excluirUsuario(Long id) {
        repository.excluir(id);
    }

    public void atualizarUsuarioCompleto(Long id, String nome, String email, String setor) {
    }
}