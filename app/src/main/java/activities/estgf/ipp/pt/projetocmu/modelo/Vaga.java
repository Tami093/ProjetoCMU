package activities.estgf.ipp.pt.projetocmu.modelo;

import java.io.Serializable;

public class Vaga implements Serializable {
    private long id;
    private String nomeVaga;
    private String nomeEmpresa;
    private String tipoVaga;
    private String salario;
    private String localTrabalho;
    private boolean vagaAtiva;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNomeVaga() { return nomeVaga; }
    public void setNomeVaga(String nome) { this.nomeVaga = nome; }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }

    public String getTipoVaga() { return tipoVaga; }
    public void setTipoVaga(String tipoVaga) { this.tipoVaga = tipoVaga; }

    public String getSalario() { return salario; }
    public void setSalario(String salario) { this.salario = salario; }

    public String getLocalTrabalho() { return localTrabalho; }
    public void setLocalTrabalho(String localTrabalho) { this.localTrabalho = localTrabalho; }

    public boolean isVagaAtiva() { return vagaAtiva; }
    public void setVagaAtiva(boolean vagaAtiva) { this.vagaAtiva = vagaAtiva; }



}