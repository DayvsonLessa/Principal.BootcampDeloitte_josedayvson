package model;

// O 'extends' indica que Administrador HERDA de Usuario
public class Administrador extends Usuario {
    private String setor;

    public Administrador(Long id, String nome, String email, String setor) {
        // O 'super' chama o construtor da classe pai (Usuario)
        super(id, nome, email);
        this.setor = setor;
    }

    public void gerenciarSistema() {
        System.out.println("Admin " + getNome() + " está acessando o painel de controle.");
    }

    public String getSetor() { return setor; }
}