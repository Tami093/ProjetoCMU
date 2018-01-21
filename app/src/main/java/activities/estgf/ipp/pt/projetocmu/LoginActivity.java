package activities.estgf.ipp.pt.projetocmu;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.database.sqlite.*;
import android.database.*;

//import java.util.logging.Logger;

import java.util.concurrent.ExecutionException;

import activities.estgf.ipp.pt.projetocmu.dao.AlunoDAO;
import activities.estgf.ipp.pt.projetocmu.dao.EmpresaDAO;
import activities.estgf.ipp.pt.projetocmu.dao.HelperDAO;
import activities.estgf.ipp.pt.projetocmu.dao.VagaDAO;
import activities.estgf.ipp.pt.projetocmu.fragments.Fragment2MapaDoVagasDeEmprego;
import activities.estgf.ipp.pt.projetocmu.fragments.Fragment2VagasDeEmprego;
import activities.estgf.ipp.pt.projetocmu.modelo.Empresa;
import android.app.ProgressDialog;

public class LoginActivity extends AppCompatActivity {

    private TextView textoSeparadorOu ,msgProgresso;
    private EditText login, senha;
    private RadioGroup radioGroupAlunoEmpresa;
    private RadioButton alunoRadio, empresaRadio;
    private Button botaoFazerLogin, botaoEsqueceuSenha, botaoRegistrar, inserirDadosBanco;
    private ProgressBar progressBarLogin;
    private HelperDAO dao;
    private SQLiteDatabase conn;

    private TarefaLogin loginTarefa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("");

        try {
            dao = new HelperDAO(this);
            conn = dao.getReadableDatabase();
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Conexao criada com sucesso!");
            dlg.setNeutralButton("OK",null);
            dlg.show();
        }
        catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o Banco: " + ex.getMessage());
            dlg.setNeutralButton("OK",null);
            dlg.show();
        }



        login = (EditText) findViewById(R.id.login_login_editText);
        senha = (EditText) findViewById(R.id.login_senha_editText);
        alunoRadio = (RadioButton) findViewById(R.id.login_aluno_radio);
        empresaRadio = (RadioButton) findViewById(R.id.login_empresa_radio);
        textoSeparadorOu = (TextView) findViewById(R.id.login_textoSeparadorOU_textview);

        // Preciso chamar o radioGroup aqui em cima para que dentro da funcao do botao login eu consiga ver qual o item selecionado
        radioGroupAlunoEmpresa = (RadioGroup) findViewById(R.id.login_radioGroup_radioGroup);

        msgProgresso = (TextView) findViewById(R.id.login_mensagemRetorno_textView);
        progressBarLogin = (ProgressBar)findViewById(R.id.login_progressBar_progressBar);
        progressBarLogin.setVisibility(View.INVISIBLE);
        msgProgresso.setVisibility(View.INVISIBLE);

        /* --Funcao para ver oq q rola quando clicar no radio escolhido! (Estava utulizando para teste) */
        radioGroupAlunoEmpresa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.login_aluno_radio){
                    //Toast.makeText(LoginActivity.this, "ALUNO RADIO" , Toast.LENGTH_LONG).show();
                    botaoEsqueceuSenha.setVisibility(View.INVISIBLE);
                    botaoRegistrar.setVisibility(View.INVISIBLE);
                    textoSeparadorOu.setVisibility(View.INVISIBLE);
                }else if(id == R.id.login_empresa_radio){
                    //Toast.makeText(LoginActivity.this, "EMPRESA RADIO" , Toast.LENGTH_LONG).show();
                    botaoEsqueceuSenha.setVisibility(View.VISIBLE);
                    botaoRegistrar.setVisibility(View.VISIBLE);
                    textoSeparadorOu.setVisibility(View.VISIBLE);
                }
            }
        });

        //Botao Login
        botaoFazerLogin = (Button) findViewById(R.id.login_botaoLogin_button);
        botaoFazerLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                int idRadioSelecionado = radioGroupAlunoEmpresa.getCheckedRadioButtonId();
                int numeroAuxRadio = 0; // 0 = aluno (padrao) . 1 = empresa.


                if((login.getText().toString().equals("") || login.getText().toString().equals(null))
                  ||senha.getText().toString().equals("") || senha.getText().toString().equals(null)){

                    Toast.makeText(LoginActivity.this, "Login ou Senha sem valor!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(idRadioSelecionado == alunoRadio.getId()){
                    AlunoDAO alunoDAO = new AlunoDAO(LoginActivity.this);

                    if(alunoDAO.existeAluno(login.getText().toString(), senha.getText().toString())){
                        // idAluno = alunoDAO.pegaIdAluno(login.getText().toString(), senha.getText().toString());
                        progressBarLogin.setVisibility(View.VISIBLE);
                        msgProgresso.setVisibility(View.VISIBLE);
                        msgProgresso.setText("Aguarde ...");
                        try {
                             wait();

                            String idAluno;
                            loginTarefa = new TarefaLogin(progressBarLogin,login, senha,LoginActivity.this);
                            idAluno= loginTarefa.execute(login.getText().toString(),senha.getText().toString()).get();
                            msgProgresso.setVisibility(View.GONE);
                            progressBarLogin.setVisibility(View.GONE);

                            Intent vaiParaVagasActivity = new Intent(LoginActivity.this, VagasDeEmpregoActivity.class);
                            vaiParaVagasActivity.putExtra("idDoAluno",idAluno);
                            startActivity(vaiParaVagasActivity);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Usuario ou Senha invalidos!", Toast.LENGTH_LONG).show();
                    }


                }else if(idRadioSelecionado ==  empresaRadio.getId()){
                    EmpresaDAO empresaDAO = new EmpresaDAO(LoginActivity.this);

                    //Verifica se a empresa esta cadastrada
                    if(empresaDAO.ehEmpresaCadastrada(login.getText().toString(), senha.getText().toString())){
                        Empresa empresa = new Empresa();
                        empresa = empresaDAO.pegaEmpresa(login.getText().toString(), senha.getText().toString());

                        Intent vaiParaMainEmpresasActivy = new Intent(LoginActivity.this, MainEmpresasActivity.class);
                        vaiParaMainEmpresasActivy.putExtra("idDaEmpresa",empresa.getId());
                        startActivity(vaiParaMainEmpresasActivy);
                        //Toast.makeText(LoginActivity.this,"!!Esta Cadastrado No Banco de Dados!!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(LoginActivity.this,"Usuario ou Senha invalidos!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        //Botao Esqueceu a senha
        botaoEsqueceuSenha = (Button) findViewById(R.id.login_botaoEsqueceuSenha_button);
        botaoEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaEsqueceuSenha = new Intent(LoginActivity.this, EsqueceuSenhaActivity.class);
                startActivity(vaiParaEsqueceuSenha);
                //Toast.makeText(LoginActivity.this, "Clicou Botao Esqueceu a senha", Toast.LENGTH_LONG).show();
                //Intent vaiParaMapa = new Intent(LoginActivity.this, Fragment2VagasDeEmprego.class);
                //startActivity(vaiParaMapa);
            }
        });

        botaoRegistrar = (Button) findViewById(R.id.login_botaoRegistrar_button);
        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaCadastro= new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(vaiParaCadastro);
            }
        });
    }
    private void exibiProgresso(boolean statusProgressBar, boolean statusMensagem){
        if (statusProgressBar == true ){
            progressBarLogin.setVisibility(View.VISIBLE);
        }else{
            progressBarLogin.setVisibility(View.GONE);
        }

        if (statusMensagem == true ){
            msgProgresso.setVisibility(View.VISIBLE);
        }else{
            msgProgresso.setVisibility(View.GONE);
        }

    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login_activity, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogin_addicionarBanco:
                //Toast.makeText(LoginActivity.this, "Tocou em BancoDados", Toast.LENGTH_LONG).show();
                VagaDAO x = new VagaDAO(LoginActivity.this);
                x.insereVagasAutomaticoAoCriarBanco();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
