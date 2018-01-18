package activities.estgf.ipp.pt.projetocmu.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        values.put("senha", aluno.getSenha());

        dao.getWritableDatabase().insert("Aluno",null,values);
        dao.close();
    }

    public boolean existeAluno(String email, String senha){
        dao = new HelperDAO(contexto);

        Cursor c =  dao.getReadableDatabase().rawQuery("SELECT * FROM ALUNO WHERE email = ? and senha = ?", new String[]{email,senha} );
        int resultado = c.getCount();
        c.close();

        return resultado > 0;
    }

    public long pegaIdAluno(String email, String senha){
        dao = new HelperDAO(contexto);

        Cursor c =  dao.getReadableDatabase().rawQuery("SELECT * FROM ALUNO WHERE email = ? and senha = ?", new String[]{email,senha} );
        Aluno aluno = new Aluno();

        while (c.moveToNext()) {
            aluno.setIdAluno(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEmail(c.getString(c.getColumnIndex("email")));
            aluno.setSenha(c.getString(c.getColumnIndex("senha")));

        }
        c.close();
        return aluno.getIdAluno();
    }

    public Aluno pegarUnicoAluno (long idAluno){
        dao = new HelperDAO(contexto);

        Cursor c =  dao.getReadableDatabase().rawQuery("SELECT * FROM ALUNO WHERE id = ?", new String[]{String.valueOf(idAluno)} );
        Aluno aluno = new Aluno();

        while (c.moveToNext()) {
            aluno.setIdAluno(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEmail(c.getString(c.getColumnIndex("email")));
            aluno.setSenha(c.getString(c.getColumnIndex("senha")));
        }
        c.close();
        return aluno;
    }
}
