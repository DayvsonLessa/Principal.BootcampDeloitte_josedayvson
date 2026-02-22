package model;

import jakarta.persistence.*;

@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {

    private String setor;


    public Administrador() {}

    public Administrador(Long id, String nome, String email, String setor) {
        super(id, nome, email);
        this.setor = setor;
    }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
}



