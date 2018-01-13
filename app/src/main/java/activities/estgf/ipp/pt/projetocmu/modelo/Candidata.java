package activities.estgf.ipp.pt.projetocmu.modelo;

import java.io.Serializable;
import java.util.List;

public class Candidata implements Serializable {

    private long id;
    private long idAluno;
    private long idVaga;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getIdAluno() { return idAluno; }
    public void setIdAluno(long idAluno) { this.idAluno = idAluno; }

    public long getIdVaga() { return idVaga; }
    public void setIdVaga(long idVaga) { this.idVaga = idVaga; }

}
