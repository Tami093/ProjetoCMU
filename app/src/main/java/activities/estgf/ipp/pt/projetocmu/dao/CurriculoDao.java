package activities.estgf.ipp.pt.projetocmu.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import activities.estgf.ipp.pt.projetocmu.modelo.Curriculo;

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


    public void atualizaCurriculo(Curriculo curriculo){
        dao = new HelperDAO(context);
        ContentValues dados = pegaDadosCurriculo(curriculo);
        String[] params = {curriculo.getId().toString()};
        dao.getWritableDatabase().update("CURRICULO",dados,"id = ?",params);
        dao.close();
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
        c.close();
        return curriculo;
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
        dados.put("cargo",curriculo.getCargo());
        dados.put("periodo",curriculo.getPerido());
        dados.put("idioma1",curriculo.getIdioma1());
        dados.put("idioma2",curriculo.getIdioma2());
        dados.put("idAluno",curriculo.getIdAluno());

        return dados;
    }


    public void printaCurriculo(Curriculo curriculo){
        System.out.println(curriculo.getId());
        System.out.println(curriculo.getNome());
        System.out.println(curriculo.getDataNasc());
        System.out.println(curriculo.getSexo());
        System.out.println(curriculo.getTelefone());
        System.out.println(curriculo.getEmail());
        System.out.println(curriculo.getEnderenco());
        System.out.println(curriculo.getObejtivo());
        System.out.println(curriculo.getCurso());
        System.out.println(curriculo.getEmpresa());
        System.out.println(curriculo.getCargo());
        System.out.println(curriculo.getPerido());
        System.out.println(curriculo.getIdioma1());
        System.out.println(curriculo.getIdioma2());
        System.out.println(curriculo.getIdAluno());
    }
}
