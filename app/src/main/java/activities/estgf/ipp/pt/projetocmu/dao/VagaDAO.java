package activities.estgf.ipp.pt.projetocmu.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class VagaDAO {

    private Context contexto;
    private HelperDAO dao;

    public VagaDAO(Context contexto) {
        this.contexto = contexto;
    }

    public List<Vaga> buscaVagas() {
        String sql = "SELECT * FROM Vaga;";
        dao = new HelperDAO(contexto);
        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);

        List<Vaga> vagas = new ArrayList<Vaga>();
        while (c.moveToNext()){
            Vaga vaga = new Vaga();
            vaga.setId(c.getLong(c.getColumnIndex("id")));
            vaga.setNomeVaga(c.getString(c.getColumnIndex("nomevaga")));
            vaga.setNomeEmpresa(c.getString(c.getColumnIndex("nomeempresa")));
            vaga.setTipoVaga(c.getString(c.getColumnIndex("tipovaga")));
            vaga.setSalario(c.getString(c.getColumnIndex("salario")));
            vaga.setLocalTrabalho(c.getString(c.getColumnIndex("localtrabalho")));
            vaga.setVagaAtiva(c.getString(c.getColumnIndex("vagaativa")));
            vaga.setIdEmpresa(c.getLong(c.getColumnIndex("idEmpresa")));

            vagas.add(vaga);
        }
        c.close();
        return vagas;
    }

    public List<Vaga> buscaVagasDaEmpresa(long idDaEmpresa){
        List<Vaga> vagas = new ArrayList<Vaga>();
        dao = new HelperDAO(contexto);

        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM VAGA WHERE idEmpresa = ?", new String[]{String.valueOf(idDaEmpresa)});
        while (c.moveToNext()){
            Vaga vaga = new Vaga();
            vaga.setId(c.getLong(c.getColumnIndex("id")));
            vaga.setNomeVaga(c.getString(c.getColumnIndex("nomevaga")));
            vaga.setNomeEmpresa(c.getString(c.getColumnIndex("nomeempresa")));
            vaga.setTipoVaga(c.getString(c.getColumnIndex("tipovaga")));
            vaga.setSalario(c.getString(c.getColumnIndex("salario")));
            vaga.setLocalTrabalho(c.getString(c.getColumnIndex("localtrabalho")));
            vaga.setVagaAtiva(c.getString(c.getColumnIndex("vagaativa")));
            vaga.setIdEmpresa(c.getLong(c.getColumnIndex("idEmpresa")));
            vagas.add(vaga);
        }
        c.close();

        return vagas;
    }

}
