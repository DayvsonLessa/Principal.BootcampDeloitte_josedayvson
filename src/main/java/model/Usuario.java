package model;

import jakarta.persistence.*; // Importações do JPA

@Entity // Diz que esta classe é uma tabela no banco
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED) // Estratégia de Herança
public class Usuario {

    @Id // Define como Chave Primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
    private Long id;

    @Column(nullable = false) // Nome não pode ser vazio
    private String nome;

    @Column(unique = true, nullable = false) // Email único e obrigatório
    private String email;

    // Construtor vazio obrigatório pelo JPA
    public Usuario() {}

    public Usuario(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters (Mantenha os que você já tem)
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
}


