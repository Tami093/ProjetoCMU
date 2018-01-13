package activities.estgf.ipp.pt.projetocmu.dao;

import android.content.ContentValues;
import android.content.Context;

import activities.estgf.ipp.pt.projetocmu.modelo.Curriculo;

/**
 * Created by Tamires on 11/01/2018.
 */

public class CurriculoDao {
    private Context context;
    private HelperDAO dao;

    public CurriculoDao(Context context) {
        this.context = context;
    }

    public void insereCurriculo(Curriculo curriculo){
        dao = new HelperDAO(context);
        ContentValues dados = new ContentValues();
        dados.put("idAluno", String.valueOf(curriculo.getIdAluno()));
        dados.put("nome",curriculo.getNome());
        dados.put("dataNasc",curriculo.getDataNasc());
        dados.put("genero",curriculo.getSexo());
        dados.put("telefone",curriculo.getTelefone());
        dados.put("email",curriculo.getEmail());
        dados.put("enderenco",curriculo.getEnderenco());
        dados.put("objetivo",curriculo.getObejtivo());
        dados.put("curso",curriculo.getCurso());
        dados.put("empresa",curriculo.getEmpresa());
        dados.put("periodo",curriculo.getPerido());
        dados.put("localEmpresa",curriculo.getLocalEmpresa());
        dados.put("idioma1",curriculo.getIdioma1());
        dados.put("idioma2",curriculo.getIdioma2());

        dao.getWritableDatabase().insert("Curriculo",null,dados);
    }
}
