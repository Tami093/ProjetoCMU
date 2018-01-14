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
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);

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

    /*
    public void insereVagasAutomaticoAoCriarBanco() {
        dao = new HelperDAO(contexto);

        List<Vaga> vagas = new ArrayList<Vaga>();

        Vaga v1 = new Vaga();
        v1.setLocalTrabalho("Rua de Santa Quiteria 15");
        v1.setNomeEmpresa("Continente ");
        v1.setSalario("1000 Euros");
        v1.setNomeVaga("Vaga Para Caixa");
        v1.setTipoVaga("Estagiario");
        v1.setVagaAtiva("true");
        vagas.add(v1);

        Vaga v2 = new Vaga();
        v2.setLocalTrabalho("Rua de Belem 15");
        v2.setNomeEmpresa("Quiosque legal");
        v2.setSalario("753 Euros");
        v2.setNomeVaga("Vaga Para Garconete");
        v2.setTipoVaga("Efetivo");
        v2.setVagaAtiva("true");
        vagas.add(v2);

        Vaga v3 = new Vaga();
        v3.setLocalTrabalho("Rua Professor Joaquim Barros Leite 100");
        v3.setNomeEmpresa("Campo Futebol Felgueiras");
        v3.setSalario("1254 Euros");
        v3.setNomeVaga("Vaga para cortador de grama");
        v3.setTipoVaga("Efetivado");
        v3.setVagaAtiva("true");
        vagas.add(v3);

        Vaga v4 = new Vaga();
        v4.setLocalTrabalho("Rua do Curral 156");
        v4.setNomeEmpresa("ESTG");
        v4.setSalario("850 Euros");
        v4.setNomeVaga("Vaga Para TI");
        v4.setTipoVaga("Estagiario");
        v4.setVagaAtiva("true");

        Vaga v5 = new Vaga();
        v5.setLocalTrabalho("Avenida Doutor Magalhães Lemos 435");
        v5.setNomeEmpresa("Pastelarias Fixe");
        v5.setSalario("1739 Euros");
        v5.setNomeVaga("Pasteleiro");
        v5.setTipoVaga("Efetivado");
        v5.setVagaAtiva("true");
        vagas.add(v5);


        for(int i = 0 ; i < vagas.size() ; i++) {
            ContentValues dados = new ContentValues();
            dados.put("nomevaga", vagas.get(i).getNomeVaga());
            dados.put("nomeempresa", vagas.get(i).getNomeEmpresa());
            dados.put("tipovaga", vagas.get(i).getTipoVaga());
            dados.put("salario", vagas.get(i).getSalario());
            dados.put("localtrabalho", vagas.get(i).getLocalTrabalho());
            dados.put("vagaativa", vagas.get(i).getVagaAtiva());

            dao.getWritableDatabase().insert("Vagas", null, dados);
        }
        dao.close();
    }
    */
}
