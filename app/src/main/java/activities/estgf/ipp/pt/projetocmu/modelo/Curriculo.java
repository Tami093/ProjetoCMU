package activities.estgf.ipp.pt.projetocmu.modelo;

import java.io.Serializable;

/**
 * Created by Tamires on 11/01/2018.
 */

public class Curriculo implements Serializable {
    private long id;
    private long idAluno;
    private String nome;
    private String dataNasc;
    private String sexo;
    private String telefone;
    private String email;
    private String enderenco;
    private String obejtivo;
    private String curso;
    private String empresa;
    private String cargo;
    private String perido;
    private String idioma1;
    private String idioma2;

    public Curriculo(long idAluno, String nome, String dataNasc, String sexo, String telefone, String email, String enderenco, String obejtivo, String curso, String empresa, String cargo, String perido, String idioma1, String idioma2) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.enderenco = enderenco;
        this.obejtivo = obejtivo;
        this.curso = curso;
        this.empresa = empresa;
        this.cargo = cargo;
        this.perido = perido;
        this.idioma1 = idioma1;
        this.idioma2 = idioma2;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public long getIdAluno() {return idAluno;}
    public void setIdAluno(long idAluno) {this.idAluno = idAluno;}

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

    public String getCargo() {return cargo;}
    public void setCargo(String cargo) {this.cargo = cargo;}

    public String getPerido() {return perido;}
    public void setPerido(String perido) {this.perido = perido;}

    public String getIdioma1() {return idioma1;}
    public void setIdioma1(String idioma1) {
        this.idioma1 = idioma1;}

    public String getIdioma2() {return idioma2;}
    public void setIdioma2(String idioma2) {this.idioma2 = idioma2;}
}
