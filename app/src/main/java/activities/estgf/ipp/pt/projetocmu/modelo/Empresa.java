package activities.estgf.ipp.pt.projetocmu.modelo;

import java.io.Serializable;

/**
 * Created by VHugo on 13/12/2017.
 */

public class Empresa implements Serializable {
    private long id;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
