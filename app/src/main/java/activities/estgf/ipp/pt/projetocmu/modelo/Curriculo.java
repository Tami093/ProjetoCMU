package activities.estgf.ipp.pt.projetocmu.modelo;

import java.io.Serializable;

/**
 * Created by Tamires on 11/01/2018.
 */

public class Curriculo implements Serializable {
    private long id;
    private Aluno idAluno;
    private String nome;
    private String dataNasc;
    private String sexo;
    private String telefone;
    private String email;
    private String enderenco;
    private String obejtivo;
    private String curso;
    private String empresa;
    private String localEmpresa;
    private String perido;
    private String idioma1;
    private String idioma2;



    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public Aluno getIdAluno() {return idAluno;}
    public void setIdAluno(Aluno idAluno) {this.idAluno = idAluno;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDataNasc() {return dataNasc;}

    public void setDataNasc(String dataNasc) {this.dataNasc = dataNasc;}

    public String getSexo() {return sexo;}
    public void setSexo(String sexo) {this.sexo = sexo;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getEnderenco() {return enderenco;}
    public void setEnderenco(String enderenco) {this.enderenco = enderenco;}

    public String getObejtivo() {return obejtivo;}

    public void setObejtivo(String obejtivo) {this.obejtivo = obejtivo;}
    public String getCurso() {return curso;}
    public void setCurso(String curso) {this.curso = curso;}

    public String getEmpresa() {return empresa;}
    public void setEmpresa(String empresa) {this.empresa = empresa;}

    public String getLocalEmpresa() {return localEmpresa;}
    public void setLocalEmpresa(String localEmpresa) {this.localEmpresa = localEmpresa;}

    public String getPerido() {return perido;}
    public void setPerido(String perido) {this.perido = perido;}

    public String getIdioma1() {return idioma1;}
    public void setIdioma1(String idioma1) {
        this.idioma1 = idioma1;}

    public String getIdioma2() {return idioma2;}
    public void setIdioma2(String idioma2) {this.idioma2 = idioma2;}
}
