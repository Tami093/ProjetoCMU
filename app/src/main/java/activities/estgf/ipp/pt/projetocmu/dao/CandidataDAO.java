package activities.estgf.ipp.pt.projetocmu.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.modelo.Candidata;

public class CandidataDAO {

    private Context contexto;
    private HelperDAO dao;

    public CandidataDAO(Context contexto) { this.contexto = contexto; }


    public List<Candidata> buscaTodosCandidatos() {
        String sql = "SELECT * FROM CANDIDATA;";
        dao = new HelperDAO(contexto);
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);

        List<Candidata> candidatos = new ArrayList<Candidata>();
        while (c.moveToNext()){
            Candidata candidato = new Candidata();
            candidato.setId(c.getLong(c.getColumnIndex("id")));
            candidato.setIdAluno(c.getLong(c.getColumnIndex("idAluno")));
            candidato.setIdVaga(c.getLong(c.getColumnIndex("idVaga")));

            candidatos.add(candidato);
        }

        c.close();
        dao.close();
        return candidatos;
    }

    public String quantidadeDeCandidatos(String id){
        dao = new HelperDAO(contexto);
        Cursor c =
                dao.getReadableDatabase().rawQuery("SELECT * FROM CANDIDATA WHERE idVagaEmp = ?", new String[]{id} );
        String resultado = String.valueOf(c.getCount());
        c.close();

        return resultado;
    }

    public List<Long> todosAlunosParaAquelaVaga(Long idEmpresa){
        dao = new HelperDAO(contexto);
        List<Long> listaIdsAlunos = new ArrayList<Long>();

        Cursor c =
                dao.getReadableDatabase().rawQuery("SELECT * FROM CANDIDATA WHERE idVagaEmp = ?", new String[]{idEmpresa.toString()} );
        while (c.moveToNext()){
            long idAluno = c.getLong(c.getColumnIndex("idAluno"));
/*
            System.out.println("DENTRO DO DAO>>>>>>>");
            System.out.println(idAluno);
            System.out.println("<<<<<<<DENTRO DO DAO");
*/
            listaIdsAlunos.add(idAluno);
        }
        c.close();

        return listaIdsAlunos;
    }
    public ContentValues pegaDadosCandidata(Candidata candidata){
        ContentValues dadosCandidata = new ContentValues();

        dadosCandidata.put("idAluno",candidata.getIdAluno());
        dadosCandidata.put("idVagaEmp",candidata.getIdVaga());

        return dadosCandidata;
    }
    public  void insereCandidatura(Candidata candidata){
        dao = new HelperDAO(contexto);
        ContentValues dados = pegaDadosCandidata(candidata);
        dao.getWritableDatabase().insert("CANDIDATA",null,dados);
        dao.close();
    }
}
