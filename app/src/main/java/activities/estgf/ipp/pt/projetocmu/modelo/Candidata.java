package activities.estgf.ipp.pt.projetocmu.modelo;

import java.io.Serializable;
import java.util.List;

public class Candidata implements Serializable {

    private Long id;
    private Long idAluno;
    private Long idVaga;

    public Long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Long getIdAluno() { return idAluno; }
    public void setIdAluno(long idAluno) { this.idAluno = idAluno; }

    public Long getIdVaga() { return idVaga; }
    public void setIdVaga(long idVaga) { this.idVaga = idVaga; }

}
