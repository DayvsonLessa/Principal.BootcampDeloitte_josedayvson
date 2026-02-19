package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Usuario;
import java.util.List;

public class UsuarioRepository {
    // O nome "meu-pu" deve ser o mesmo que você colocou no persistence.xml
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meu-pu");

    public void salvar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario); // O JPA faz o INSERT automático aqui!
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Usuario> listarTodos() {
        EntityManager em = emf.createEntityManager();
        // JPQL: Uma linguagem de consulta parecida com SQL, mas focada em Objetos
        List<Usuario> usuarios = em.createQuery("FROM Usuario", Usuario.class).getResultList();
        em.close();
        return usuarios;
    }

    public Usuario buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Usuario u = em.find(Usuario.class, id); // O JPA faz o SELECT * FROM ... WHERE ID = ?
        em.close();
        return u;
    }

    public void atualizar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario); // O JPA faz o UPDATE automático!
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void excluir(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario u = em.find(Usuario.class, id);
            if (u != null) {
                em.remove(u); // O JPA faz o DELETE!
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
