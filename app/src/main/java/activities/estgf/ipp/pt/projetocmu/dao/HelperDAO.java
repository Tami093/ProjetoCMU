package activities.estgf.ipp.pt.projetocmu.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class HelperDAO extends SQLiteOpenHelper {

    private String sqlCriacaoTabelas;


    public HelperDAO(Context context) {
        super(context, "ProjetoCMUBancoDados", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sqlCriacaoTabelas = "CREATE TABLE IF NOT EXISTS VAGAS (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nomevaga TEXT NOT NULL," +
                "nomeempresa TEXT," +
                "tipovaga TEXT," +
                "salario TEXT," +
                "localtrabalho TEXT," +
                "vagaativa TEXT);";
        db.execSQL(sqlCriacaoTabelas);


        sqlCriacaoTabelas = "CREATE TABLE IF NOT EXISTS Empresas " +
                "(id INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "email TEXT," +
                "endereco TEXT," +
                "telefone TEXT);";

        db.execSQL(sqlCriacaoTabelas);


        /*
            sqlCriacaoTabelas = "CREATE TABLE IF NOT EXISTS Alunos ";
            db.execSQL(sqlCriacaoTabelas);
        */

        /*
            sqlCriacaoTabelas = "CREATE TABLE IF NOT EXISTS Candidata ";
            db.execSQL(sqlCriacaoTabelas);
        */

        /*
            sqlCriacaoTabelas = "CREATE TABLE IF NOT EXISTS Curriculo ";
            db.execSQL(sqlCriacaoTabelas);
        */



        //Insersao na base de dados os valores!
        insereVagasAutomaticoAoCriarBanco(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        /*
        String sql = "";
        switch (versaoAntiga){  //Geralmente nao se usa os breack no switch, pois vai rodar todas as versoes do banco
            case 1:
                sql = "ALTER TABLE Alunos ADD COLUMN caminhoFoto TEXT";
                db.execSQL(sql);
        }
        */
    }

    public void insereVagasAutomaticoAoCriarBanco(SQLiteDatabase db) {
        List<Vaga> vagas = new ArrayList<Vaga>();

        Vaga v1 = new Vaga();
        v1.setLocalTrabalho("Rua de Santa Quiteria 15");
        v1.setNomeEmpresa("Continente ");
        v1.setSalario("1000");
        v1.setNomeVaga("Vaga Para Caixa");
        v1.setTipoVaga("Estagiario");
        v1.setVagaAtiva("true");
        vagas.add(v1);

        Vaga v2 = new Vaga();
        v2.setLocalTrabalho("Rua de Belem 15");
        v2.setNomeEmpresa("Quiosque legal");
        v2.setSalario("753");
        v2.setNomeVaga("Vaga Para Garconete");
        v2.setTipoVaga("Efetivo");
        v2.setVagaAtiva("true");
        vagas.add(v2);

        Vaga v3 = new Vaga();
        v3.setLocalTrabalho("Rua Professor Joaquim Barros Leite 100");
        v3.setNomeEmpresa("Campo Futebol Felgueiras");
        v3.setSalario("1254");
        v3.setNomeVaga("Vaga para cortador de grama");
        v3.setTipoVaga("Efetivado");
        v3.setVagaAtiva("true");
        vagas.add(v3);

        Vaga v4 = new Vaga();
        v4.setLocalTrabalho("Rua do Curral 156");
        v4.setNomeEmpresa("ESTG");
        v4.setSalario("850");
        v4.setNomeVaga("Vaga Para TI");
        v4.setTipoVaga("Estagiario");
        v4.setVagaAtiva("true");

        Vaga v5 = new Vaga();
        v5.setLocalTrabalho("Avenida Doutor Magalhães Lemos 435");
        v5.setNomeEmpresa("Pastelarias Fixe");
        v5.setSalario("1739");
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

            db.insert("Vagas", null, dados);
        }
    }
}
