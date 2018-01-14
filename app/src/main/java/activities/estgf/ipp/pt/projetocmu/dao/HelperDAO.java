package activities.estgf.ipp.pt.projetocmu.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.modelo.Aluno;
import activities.estgf.ipp.pt.projetocmu.modelo.Candidata;
import activities.estgf.ipp.pt.projetocmu.modelo.Empresa;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class HelperDAO extends SQLiteOpenHelper {

    private String sqlCriacaoTabelas;


    public HelperDAO(Context context) {
        super(context, "ProjetoCMUBancoDados", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        // Criacao da Tabela de EMPRESA
        sqlCriacaoTabelas = "CREATE TABLE EMPRESA (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "senha TEXT NOT NULL," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "endereco TEXT NOT NULL," +
                "telefone TEXT NOT NULL," +
                "nif TEXT NOT NULL);";


        db.execSQL(sqlCriacaoTabelas);
        
        // Criacao da Tabela de VAGA
        sqlCriacaoTabelas = "CREATE TABLE VAGA (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nomevaga TEXT NOT NULL," +
                "nomeempresa TEXT NOT NULL," +
                "tipovaga TEXT NOT NULL," +
                "salario TEXT NOT NULL," +
                "localtrabalho TEXT NOT NULL," +
                "vagaativa TEXT NOT NULL," +
                "idEmpresa INTEGER NOT NULL," +
                "FOREIGN KEY(idEmpresa) REFERENCES EMPRESA(id));";

        db.execSQL(sqlCriacaoTabelas);

        //Criacao da tabela ALUNO
        sqlCriacaoTabelas = "CREATE TABLE IF NOT EXISTS ALUNO(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL,"+
                "email TEXT NOT NULL);";

        db.execSQL(sqlCriacaoTabelas);

        // Criacao da Tabela de CURRICULO
        sqlCriacaoTabelas = "CREATE TABLE IF NOT EXISTS CURRICULO(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "dataNasc TEXT NOT NULL,"+
                    "genero TEXT NOT NULL,"+
                    "telefone TEXT NOT NULL,"+
                    "email TEXT NOT NULL,"+
                    "enderenco TEXT NOT NULL,"+
                    "objetivo TEXT NOT NULL,"+
                    "curso TEXT NOT NULL,"+
                    "empresa TEXT NOT NULL,"+
                    "periodo TEXT NOT NULL,"+
                    "localEmpresa TEXT NOT NULL,"+
                    "idioma1 TEXT NOT NULL,"+
                    "idioma2 TEXT NOT NULL,"+
                    "idAluno INTEGER NOT NULL,"+
                    "FOREIGN KEY(idAluno)REFERENCES ALUNO(id));";

        db.execSQL(sqlCriacaoTabelas);



        //Criacao da tabela CANDIDATA
        sqlCriacaoTabelas = "CREATE TABLE CANDIDATA (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "idAluno INTEGER NOT NULL," +
                "idVagaEmp INTEGER NOT NULL," +
                "FOREIGN KEY(idAluno) REFERENCES ALUNO(id)," +
                "FOREIGN KEY(idVagaEmp) REFERENCES VAGA(id));";
        db.execSQL(sqlCriacaoTabelas);





        //Insersao na base de dados os valores!
        insereEmpresasAutomaticoAoCriarBanco(db);
        insereVagasAutomaticoAoCriarBanco(db);
        insereAlunosAutomaticoAoCriarBanco(db);
        insereCandidataAutomaticoAoCriarBanco(db);
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


    private void insereEmpresasAutomaticoAoCriarBanco (SQLiteDatabase db){
        List<Empresa> empresas = new ArrayList<Empresa>();

        Empresa e1 = new Empresa();
        e1.setSenha("a");
        e1.setNome("Continente");
        e1.setEmail("a");
        e1.setEndereco("R. Prof. Joaquim Barros Leite, Felgueiras");
        e1.setTelefone("951753624");
        e1.setNif("4458256954");
        empresas.add(e1);

        Empresa e2 = new Empresa();
        e2.setSenha("senhaPingoDoce");
        e2.setNome("Pingo Doce");
        e2.setEmail("pingoDoce@pingoDoce.com");
        e2.setEndereco("Rua D. Gomes D Aciegas, Felgueiras");
        e2.setTelefone("987456321");
        e2.setNif("45236995485");
        empresas.add(e2);

        for(int i = 0 ; i < empresas.size() ; i++) {
            ContentValues dados = new ContentValues();
            dados.put("senha", empresas.get(i).getSenha());
            dados.put("nome", empresas.get(i).getNome());
            dados.put("email", empresas.get(i).getEmail());
            dados.put("endereco", empresas.get(i).getEndereco());
            dados.put("telefone", empresas.get(i).getTelefone());
            dados.put("nif", empresas.get(i).getNif());

            db.insert("Empresa", null, dados);
        }

    }


    private void insereAlunosAutomaticoAoCriarBanco (SQLiteDatabase db){
        List<Aluno> alunos = new ArrayList<Aluno>();

        Aluno a1 = new Aluno();
        a1.setNome("Vitor Hugo");
        a1.setEmail("vh@gmail.com");
        alunos.add(a1);

        Aluno a2 = new Aluno();
        a2.setNome("Tamires Cristina");
        a2.setEmail("tc@gmail.com");
        alunos.add(a2);

        Aluno a3 = new Aluno();
        a3.setNome("Caio Correa");
        a3.setEmail("cc@gmail.com");
        alunos.add(a3);

        Aluno a4 = new Aluno();
        a4.setNome("Tamara Oliveira");
        a4.setEmail("to@gmail.com");
        alunos.add(a4);

        Aluno a5 = new Aluno();
        a5.setNome("Carlos Eduardo");
        a5.setEmail("ce@gmail.com");
        alunos.add(a5);

        Aluno a6 = new Aluno();
        a6.setNome("Janaina Carlinhos");
        a6.setEmail("jc@gmail.com");
        alunos.add(a6);

        for(int i = 0 ; i < alunos.size() ; i++) {
            ContentValues dados = new ContentValues();
            dados.put("nome", alunos.get(i).getNome());
            dados.put("email", alunos.get(i).getEmail());

            db.insert("Aluno", null, dados);
        }
    }

    private void insereVagasAutomaticoAoCriarBanco(SQLiteDatabase db) {
        List<Vaga> vagas = new ArrayList<Vaga>();

        Vaga v1 = new Vaga();
        v1.setLocalTrabalho("Rua de Santa Quiteria , Felgueiras");
        v1.setNomeEmpresa("Continente");
        v1.setSalario("1000");
        v1.setNomeVaga("Vaga Para Caixa");
        v1.setTipoVaga("Estagiario");
        v1.setVagaAtiva("true");
        v1.setIdEmpresa(1);
        vagas.add(v1);

        Vaga v2 = new Vaga();
        v2.setLocalTrabalho("Rua de Belem , Felgueiras");
        v2.setNomeEmpresa("Quiosque legal");
        v2.setSalario("753");
        v2.setNomeVaga("Vaga Para Garconete");
        v2.setTipoVaga("Efetivo");
        v2.setVagaAtiva("true");
        v2.setIdEmpresa(1);
        vagas.add(v2);

        Vaga v3 = new Vaga();
        v3.setLocalTrabalho("Rua Professor Joaquim Barros Leite, Felgueiras");
        v3.setNomeEmpresa("Campo Futebol Felgueiras");
        v3.setSalario("1254");
        v3.setNomeVaga("Vaga para cortador de grama");
        v3.setTipoVaga("Efetivado");
        v3.setVagaAtiva("true");
        v3.setIdEmpresa(2);
        vagas.add(v3);

        Vaga v4 = new Vaga();
        v4.setLocalTrabalho("Rua do Curral , Felgueiras");
        v4.setNomeEmpresa("ESTG");
        v4.setSalario("850");
        v4.setNomeVaga("Vaga Para TI");
        v4.setTipoVaga("Estagiario");
        v4.setVagaAtiva("true");
        v4.setIdEmpresa(2);
        vagas.add(v4);

        Vaga v5 = new Vaga();
        v5.setLocalTrabalho("Avenida Doutor Magalhães Lemos , Felgueiras");
        v5.setNomeEmpresa("Pastelarias Fixe");
        v5.setSalario("1739");
        v5.setNomeVaga("Pasteleiro");
        v5.setTipoVaga("Efetivado");
        v5.setVagaAtiva("true");
        v5.setIdEmpresa(1);
        vagas.add(v5);

        for(int i = 0 ; i < vagas.size() ; i++) {
            ContentValues dados = new ContentValues();
            dados.put("nomevaga", vagas.get(i).getNomeVaga());
            dados.put("nomeempresa", vagas.get(i).getNomeEmpresa());
            dados.put("tipovaga", vagas.get(i).getTipoVaga());
            dados.put("salario", vagas.get(i).getSalario());
            dados.put("localtrabalho", vagas.get(i).getLocalTrabalho());
            dados.put("vagaativa", vagas.get(i).getVagaAtiva());
            dados.put("idEmpresa",vagas.get(i).getIdEmpresa());

            db.insert("VAGA", null, dados);
        }
    }



    private void insereCandidataAutomaticoAoCriarBanco (SQLiteDatabase db){
        List<Candidata> candidatas = new ArrayList<Candidata>();

        Candidata c1 = new Candidata();
        c1.setIdAluno(1);
        c1.setIdVaga(1);
        candidatas.add(c1);

        Candidata c2 = new Candidata();
        c2.setIdAluno(1);
        c2.setIdVaga(2);
        candidatas.add(c2);

        Candidata c3 = new Candidata();
        c3.setIdAluno(2);
        c3.setIdVaga(1);
        candidatas.add(c3);

        Candidata c4 = new Candidata();
        c4.setIdAluno(2);
        c4.setIdVaga(2);
        candidatas.add(c4);

        Candidata c5 = new Candidata();
        c5.setIdAluno(3);
        c5.setIdVaga(1);
        candidatas.add(c5);


        for(int i = 0 ; i < candidatas.size() ; i++) {
            ContentValues dados = new ContentValues();
            dados.put("idAluno", candidatas.get(i).getIdAluno());
            dados.put("idVagaEmp", candidatas.get(i).getIdVaga());

            db.insert("CANDIDATA", null, dados);
        }

    }
}
