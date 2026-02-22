package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(Usuario usuario) {
        em.persist(usuario);
    }

    public List<Usuario> listarTodos() {
        return em.createQuery("FROM Usuario", Usuario.class).getResultList();
    }

    public Usuario buscarPorId(Long id) {
        return em.find(Usuario.class, id);
    }

    @Transactional
    public void atualizar(Usuario usuario) {
        em.merge(usuario);
    }

    @Transactional
    public void excluir(Long id) {
        Usuario u = em.find(Usuario.class, id);
        if (u != null) {
            em.remove(u);
        }
    }
}