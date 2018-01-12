package activities.estgf.ipp.pt.projetocmu.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.modelo.Empresa;

public class EmpresaDAO {

    private Context contexto;
    private HelperDAO dao;

    public EmpresaDAO(Context contexto) { this.contexto = contexto; }

    public List<Empresa> buscaEmpresas() {
        String sql = "SELECT * FROM EMPRESAS;";
        dao = new HelperDAO(contexto);
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);

        List<Empresa> empresas = new ArrayList<Empresa>();
        while (c.moveToNext()){
            Empresa empresa = new Empresa();
            empresa.setId(c.getLong(c.getColumnIndex("id")));
            empresa.setSenha(c.getString(c.getColumnIndex("senha")));
            empresa.setNome(c.getString(c.getColumnIndex("nome")));
            empresa.setEmail(c.getString(c.getColumnIndex("email")));
            empresa.setEndereco(c.getString(c.getColumnIndex("endereco")));
            empresa.setTelefone(c.getString(c.getColumnIndex("telefone")));
            empresa.setNif(c.getString(c.getColumnIndex("nif")));

            empresas.add(empresa);
        }
        c.close();
        dao.close();
        return empresas;
    }



    public void insere(Empresa empresa) {
        dao = new HelperDAO(contexto);

        ContentValues dados = pegaDadosDaEmpresa(empresa);

        dao.getWritableDatabase().insert("EMPRESAS", null, dados);

        dao.close();
    }


    @NonNull
    private ContentValues pegaDadosDaEmpresa(Empresa empresa) {
        ContentValues dados = new ContentValues();
        dados.put("senha",empresa.getSenha());
        dados.put("nome",empresa.getNome());
        dados.put("email",empresa.getEmail());
        dados.put("endereco",empresa.getEndereco());
        dados.put("telefone",empresa.getTelefone());
        dados.put("nif", empresa.getNif());
        return dados;
    }
}
