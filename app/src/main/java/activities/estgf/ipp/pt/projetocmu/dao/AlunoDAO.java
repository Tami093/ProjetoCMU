package activities.estgf.ipp.pt.projetocmu.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import activities.estgf.ipp.pt.projetocmu.modelo.Aluno;

public class AlunoDAO {
    private Context contexto;
    private HelperDAO dao;

    public AlunoDAO(Context contexto) {this.contexto = contexto;}

    public void insereAluno(Aluno aluno){
        dao = new HelperDAO(contexto);
        ContentValues values = new ContentValues();
        values.put("nome",aluno.getNome());
        values.put("email",aluno.getNome());

        dao.getWritableDatabase().insert("Aluno",null,values);
        dao.close();
    }

}
