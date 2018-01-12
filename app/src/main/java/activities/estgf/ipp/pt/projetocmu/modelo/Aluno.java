package activities.estgf.ipp.pt.projetocmu.modelo;

import java.io.Serializable;

public class Aluno implements Serializable{
    private long idAluno;
    private String nome;
    private String email;

    public long getIdAluno() {return idAluno;}
    public void setIdAluno(long idAluno){this.idAluno = idAluno;}

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
}
