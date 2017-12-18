package activities.estgf.ipp.pt.projetocmu.modelo;

import java.io.Serializable;
import java.util.List;

public class Candidata implements Serializable {

    private long id;
    private List<Aluno> alunos;
    private List<Vaga> vagas;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public List<Aluno> getAlunos() { return alunos; }
    public void setAlunos(List<Aluno> alunos) { this.alunos = alunos; }

    public List<Vaga> getVagas() { return vagas; }
    public void setVagas(List<Vaga> vagas) { this.vagas = vagas; }

}
