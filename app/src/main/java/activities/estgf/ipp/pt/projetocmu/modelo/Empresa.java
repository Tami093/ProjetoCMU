package activities.estgf.ipp.pt.projetocmu.modelo;

import java.io.Serializable;

public class Empresa implements Serializable {
    private long id;
    private String senha;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private String nif;

    public Empresa() {}

    public Empresa(String senha, String nome, String email, String endereco, String telefone, String nif) {
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.nif = nif;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }

}
