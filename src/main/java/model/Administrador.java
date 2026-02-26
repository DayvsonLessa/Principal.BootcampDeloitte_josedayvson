package model;

import jakarta.persistence.*;

@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {

    private String setor;

    // 1. Construtor vazio (obrigatório para o Hibernate/JPA)
    public Administrador() {
        super();
    }

    // 2. Construtor completo (Caso você decida usar o setor depois)
    public Administrador(Long id, String nome, String email, String setor) {
        super(id, nome, email);
        this.setor = setor;
    }

    // 3. Construtor simplificado (Essencial para o seu formulário funcionar agora)
    public Administrador(Long id, String nome, String email) {
        super(id, nome, email);
    }

    // Getters e Setters
    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
}
