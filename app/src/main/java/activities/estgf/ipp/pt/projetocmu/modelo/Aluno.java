package activities.estgf.ipp.pt.projetocmu.modelo;

import java.io.Serializable;

/**
 * Created by VHugo on 13/12/2017.
 */

public class Aluno implements Serializable{
    private long id;
    private String nome;
    private String curriculo;
    private String email;
    private String telefone;
    private String cursos;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
