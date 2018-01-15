package activities.estgf.ipp.pt.projetocmu.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

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
        ContentValues dados = pegaDadosCurriculo(curriculo);
        dao.getWritableDatabase().insert("CURRICULO",null,dados);
        dao.close();
    }

    private ContentValues pegaDadosCurriculo(Curriculo curriculo){
        ContentValues dados = new ContentValues();

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
        dados.put("cargo",curriculo.getCargo());
        dados.put("idioma1",curriculo.getIdioma1());
        dados.put("idioma2",curriculo.getIdioma2());
        dados.put("idAluno",curriculo.getIdAluno());

        return dados;
    }

    public Curriculo existeCurriculo (long idDoAluno){
       dao  = new HelperDAO(context);
       Curriculo curriculo = new Curriculo();

        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM CURRICULO WHERE idAluno = ?", new String[]{String.valueOf(idDoAluno)});
        if(c.getCount()>0) {
            while (c.moveToNext()) {
                curriculo.setId(c.getLong(c.getColumnIndex("id")));
                curriculo.setNome(c.getString(c.getColumnIndex("nome")));
                curriculo.setDataNasc(c.getString(c.getColumnIndex("dataNasc")));
                curriculo.setSexo(c.getString(c.getColumnIndex("genero")));
                curriculo.setTelefone(c.getString(c.getColumnIndex("telefone")));
                curriculo.setEmail(c.getString(c.getColumnIndex("email")));
                curriculo.setEnderenco(c.getString(c.getColumnIndex("enderenco")));
                curriculo.setObejtivo(c.getString(c.getColumnIndex("objetivo")));
                curriculo.setCurso(c.getString(c.getColumnIndex("curso")));
                curriculo.setEmpresa(c.getString(c.getColumnIndex("empresa")));
                curriculo.setCargo(c.getString(c.getColumnIndex("cargo")));
                curriculo.setPerido(c.getString(c.getColumnIndex("periodo")));
                curriculo.setIdioma1(c.getString(c.getColumnIndex("idioma1")));
                curriculo.setIdioma2(c.getString(c.getColumnIndex("idioma2")));
                curriculo.setIdAluno(c.getLong(c.getColumnIndex("idAluno")));
            }
        }
        return curriculo;
    }

    public void atualizaCurriculo(Curriculo curriculo){
        dao = new HelperDAO(context);
        ContentValues dadosAtualizado= pegaDadosCurriculo(curriculo);
        String[] params = {String.valueOf(curriculo.getId())};
        dao.getWritableDatabase().update("CURRICULO",dadosAtualizado,"id=?",params);
    }
}
