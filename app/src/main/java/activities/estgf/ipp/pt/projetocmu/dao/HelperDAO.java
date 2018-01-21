package activities.estgf.ipp.pt.projetocmu.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.modelo.Aluno;
import activities.estgf.ipp.pt.projetocmu.modelo.Candidata;
import activities.estgf.ipp.pt.projetocmu.modelo.Curriculo;
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
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "senha TEXT NOT NULL);";

        db.execSQL(sqlCriacaoTabelas);

        // Criacao da Tabela de CURRICULO
        sqlCriacaoTabelas = "CREATE TABLE IF NOT EXISTS CURRICULO(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome      TEXT NOT NULL," +
                "dataNasc  TEXT NOT NULL," +
                "genero    TEXT NOT NULL," +
                "telefone  TEXT NOT NULL," +
                "email     TEXT NOT NULL," +
                "enderenco TEXT NOT NULL," +
                "objetivo  TEXT NOT NULL," +
                "curso     TEXT NOT NULL," +
                "empresa   TEXT NOT NULL," +
                "cargo     TEXT NOT NULL," +
                "periodo   TEXT NOT NULL," +
                "idioma1   TEXT NOT NULL," +
                "idioma2   TEXT NOT NULL," +
                "idAluno   INTEGER NOT NULL," +
                "FOREIGN KEY(idAluno)REFERENCES ALUNO(id));";

        db.execSQL(sqlCriacaoTabelas);


        //Criacao da tabela CANDIDATA
        sqlCriacaoTabelas = "CREATE TABLE IF NOT EXISTS CANDIDATA (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
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
        insereCurriculosAutomaticoAoCriarBanco(db);
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


    private void insereEmpresasAutomaticoAoCriarBanco(SQLiteDatabase db) {
        List<Empresa> empresas = new ArrayList<Empresa>();

        Empresa e1 = new Empresa();
        e1.setSenha("continente");
        e1.setNome("Continente");
        e1.setEmail("continente@gmail.com");
        e1.setEndereco("R. Prof. Joaquim Barros Leite, Felgueiras");
        e1.setTelefone("951753624");
        e1.setNif("4458256954");
        empresas.add(e1);

        Empresa e2 = new Empresa();
        e2.setSenha("pingodoce");
        e2.setNome("Pingo Doce");
        e2.setEmail("pingodoce@gmail.com");
        e2.setEndereco("Rua D. Gomes D Aciegas, Felgueiras");
        e2.setTelefone("987456321");
        e2.setNif("45236995485");
        empresas.add(e2);

        for (int i = 0; i < empresas.size(); i++) {
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


    private void insereAlunosAutomaticoAoCriarBanco(SQLiteDatabase db) {
        List<Aluno> alunos = new ArrayList<Aluno>();

        Aluno a1 = new Aluno();
        a1.setNome("Vitor Hugo");
        a1.setEmail("8170536");
        a1.setSenha("123");
        alunos.add(a1);

        Aluno a2 = new Aluno();
        a2.setNome("Tamires Cristina");
        a2.setEmail("8170535");
        a2.setSenha("123");
        alunos.add(a2);

        Aluno a3 = new Aluno();
        a3.setNome("Caio Correa");
        a3.setEmail("8170533");
        a3.setSenha("123");
        alunos.add(a3);

        Aluno a4 = new Aluno();
        a4.setNome("Tamara Oliveira");
        a4.setEmail("8170530");
        a4.setSenha("123");
        alunos.add(a4);

        Aluno a5 = new Aluno();
        a5.setNome("Carlos Eduardo");
        a5.setEmail("8170531");
        a5.setSenha("123");
        alunos.add(a5);

        Aluno a6 = new Aluno();
        a6.setNome("Janaina Carlinhos");
        a6.setEmail("8170532");
        a6.setSenha("123");
        alunos.add(a6);

        for (int i = 0; i < alunos.size(); i++) {
            ContentValues dados = new ContentValues();
            dados.put("nome", alunos.get(i).getNome());
            dados.put("email", alunos.get(i).getEmail());
            dados.put("senha", alunos.get(i).getSenha());

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

        for (int i = 0; i < vagas.size(); i++) {
            ContentValues dados = new ContentValues();
            dados.put("nomevaga", vagas.get(i).getNomeVaga());
            dados.put("nomeempresa", vagas.get(i).getNomeEmpresa());
            dados.put("tipovaga", vagas.get(i).getTipoVaga());
            dados.put("salario", vagas.get(i).getSalario());
            dados.put("localtrabalho", vagas.get(i).getLocalTrabalho());
            dados.put("vagaativa", vagas.get(i).getVagaAtiva());
            dados.put("idEmpresa", vagas.get(i).getIdEmpresa());

            db.insert("VAGA", null, dados);
        }
    }


    private void insereCandidataAutomaticoAoCriarBanco(SQLiteDatabase db) {
        List<Candidata> candidatas = new ArrayList<Candidata>();

        Candidata c1 = new Candidata();
        c1.setIdAluno(1);
        c1.setIdVaga(1);
        candidatas.add(c1);

        Candidata c2 = new Candidata();
        c2.setIdAluno(2);
        c2.setIdVaga(2);
        candidatas.add(c2);

        Candidata c3 = new Candidata();
        c3.setIdAluno(3);
        c3.setIdVaga(3);
        candidatas.add(c3);

        Candidata c4 = new Candidata();
        c4.setIdAluno(4);
        c4.setIdVaga(4);
        candidatas.add(c4);

        Candidata c5 = new Candidata();
        c5.setIdAluno(5);
        c5.setIdVaga(5);
        candidatas.add(c5);

        Candidata c6 = new Candidata();
        c6.setIdAluno(6);
        c6.setIdVaga(1);
        candidatas.add(c6);

        for (int i = 0; i < candidatas.size(); i++) {
            ContentValues dados = new ContentValues();
            dados.put("idAluno", candidatas.get(i).getIdAluno());
            dados.put("idVagaEmp", candidatas.get(i).getIdVaga());

            db.insert("CANDIDATA", null, dados);
        }
    }

    private void insereCurriculosAutomaticoAoCriarBanco(SQLiteDatabase db) {
        List<Curriculo> curriculos = new ArrayList<Curriculo>();

        Curriculo c1 = new Curriculo();
        c1.setNome("Vitor Hugo Correa");
        c1.setSexo("Masculino");
        c1.setDataNasc("12/11/1993");
        c1.setTelefone("055995844046");
        c1.setEmail("vhcorrea10@gmail.com");
        c1.setEnderenco("Rua de Santa Quiteria 100, Felgueiras");
        c1.setObejtivo("Crescer profissionalmente e pessoalmente nesta vaga que estou a candidatar");
        c1.setCurso("Engenharia da Informatica");
        c1.setEmpresa("Banco Milenium");
        c1.setCargo("Estagiario");
        c1.setPerido("1 ano");
        c1.setIdioma1("Ingles Avancado");
        c1.setIdioma2("Frances Intermediario");
        c1.setIdAluno(1);
        curriculos.add(c1);

        Curriculo c2 = new Curriculo();
        c2.setNome("Tamires Cristina de Oliveira");
        c2.setSexo("Feminino");
        c2.setDataNasc("02/05/1993");
        c2.setTelefone("055589632147");
        c2.setEmail("tamiCristina@gmail.com");
        c2.setEnderenco("Rua de Santa Quiteria 100, Felgueiras");
        c2.setObejtivo("Crescer profissionalmente e pessoalmente nesta vaga que estou a candidatar");
        c2.setCurso("Engenharia da Informatica");
        c2.setEmpresa("Banco Milenium");
        c2.setCargo("Estagiario");
        c2.setPerido("1 ano");
        c2.setIdioma1("Ingles Avancado");
        c2.setIdioma2("Frances Intermediario");
        c2.setIdAluno(2);
        curriculos.add(c2);

        Curriculo c3 = new Curriculo();
        c3.setNome("Caio Cesar");
        c3.setSexo("Masculino");
        c3.setDataNasc("08/22/1990");
        c3.setTelefone("055995888745");
        c3.setEmail("caioCesar@gmail.com");
        c3.setEnderenco("Rua de Santa Quiteria 100, Felgueiras");
        c3.setObejtivo("Crescer profissionalmente e pessoalmente nesta vaga que estou a candidatar");
        c3.setCurso("Engenharia da Informatica");
        c3.setEmpresa("Banco Milenium");
        c3.setCargo("Estagiario");
        c3.setPerido("1 ano");
        c3.setIdioma1("Ingles Avancado");
        c3.setIdioma2("Frances Intermediario");
        c3.setIdAluno(3);
        curriculos.add(c3);

        Curriculo c4 = new Curriculo();
        c4.setNome("Tamara Cristina");
        c4.setSexo("Feminino");
        c4.setDataNasc("09/23/1993");
        c4.setTelefone("055998844046");
        c4.setEmail("tamaraCristina@gmail.com");
        c4.setEnderenco("Rua de Santa Quiteria 100, Felgueiras");
        c4.setObejtivo("Crescer profissionalmente e pessoalmente nesta vaga que estou a candidatar");
        c4.setCurso("Engenharia da Informatica");
        c4.setEmpresa("Banco Milenium");
        c4.setCargo("Estagiario");
        c4.setPerido("1 ano");
        c4.setIdioma1("Ingles Avancado");
        c4.setIdioma2("Frances Intermediario");
        c4.setIdAluno(4);
        curriculos.add(c4);

        Curriculo c5 = new Curriculo();
        c5.setNome("Carlos Eduardo");
        c5.setSexo("Masculino");
        c5.setDataNasc("23/12/1993");
        c5.setTelefone("055995844099");
        c5.setEmail("carlosEdu@gmail.com");
        c5.setEnderenco("Rua de Santa Quiteria 100, Felgueiras");
        c5.setObejtivo("Crescer profissionalmente e pessoalmente nesta vaga que estou a candidatar");
        c5.setCurso("Engenharia da Informatica");
        c5.setEmpresa("Banco Milenium");
        c5.setCargo("Estagiario");
        c5.setPerido("1 ano");
        c5.setIdioma1("Ingles Avancado");
        c5.setIdioma2("Frances Intermediario");
        c5.setIdAluno(5);
        curriculos.add(c5);

        Curriculo c6 = new Curriculo();
        c6.setNome("Janaina Oliveira");
        c6.setSexo("Feminino");
        c6.setDataNasc("01/01/1993");
        c6.setTelefone("055988844046");
        c6.setEmail("janaina@gmail.com");
        c6.setEnderenco("Rua de Santa Quiteria 100, Felgueiras");
        c6.setObejtivo("Crescer profissionalmente e pessoalmente nesta vaga que estou a candidatar");
        c6.setCurso("Engenharia da Informatica");
        c6.setEmpresa("Banco Milenium");
        c6.setCargo("Estagiario");
        c6.setPerido("1 ano");
        c6.setIdioma1("Ingles Avancado");
        c6.setIdioma2("Frances Intermediario");
        c6.setIdAluno(6);
        curriculos.add(c6);



        for (int i = 0; i < curriculos.size(); i++) {
            ContentValues dados = new ContentValues();
            dados.put("nome",   curriculos.get(i).getNome());
            dados.put("dataNasc", curriculos.get(i).getDataNasc());
            dados.put("genero", curriculos.get(i).getSexo());
            dados.put("telefone", curriculos.get(i).getTelefone());
            dados.put("email", curriculos.get(i).getEmail());
            dados.put("enderenco", curriculos.get(i).getEnderenco());
            dados.put("objetivo", curriculos.get(i).getObejtivo());
            dados.put("curso", curriculos.get(i).getCurso());
            dados.put("empresa", curriculos.get(i).getEmpresa());
            dados.put("cargo", curriculos.get(i).getCargo());
            dados.put("periodo", curriculos.get(i).getPerido());
            dados.put("idioma1", curriculos.get(i).getIdioma1());
            dados.put("idioma2", curriculos.get(i).getIdioma2());
            dados.put("idAluno", curriculos.get(i).getIdAluno());

            db.insert("CURRICULO", null, dados);
        }
    }

}