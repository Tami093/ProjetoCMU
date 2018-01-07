package activities.estgf.ipp.pt.projetocmu;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.database.sqlite.*;
import android.database.*;

//import java.util.logging.Logger;

import activities.estgf.ipp.pt.projetocmu.dao.HelperDAO;
import activities.estgf.ipp.pt.projetocmu.dao.VagaDAO;

public class LoginActivity extends AppCompatActivity {

    private TextView textoSeparadorOu;
    private EditText login, senha;
    private RadioGroup radioGroupAlunoEmpresa;
    private RadioButton alunoRadio, empresaRadio;
    private Button botaoFazerLogin, botaoEsqueceuSenha, botaoRegistrar;


    private HelperDAO dao;
    private SQLiteDatabase conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



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

                Intent vaiParaEsqueceuSenha = new Intent(LoginActivity.this, VagasDeEmpregoActivity.class);
                startActivity(vaiParaEsqueceuSenha);

                if((login.getText().toString().equals("") || login.getText().toString().equals(null)) ){
                    Toast.makeText(LoginActivity.this, "Eh necessario digitar uma USUARIO", Toast.LENGTH_SHORT).show();
                    return;
                }else if(senha.getText().toString().equals("") || senha.getText().toString().equals(null)){
                    Toast.makeText(LoginActivity.this, "Eh necessario digitar uma SENHA", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(idRadioSelecionado == alunoRadio.getId()){
                    numeroAuxRadio = 0;
                }else if(idRadioSelecionado ==  empresaRadio.getId()){
                    numeroAuxRadio = 1;
                }

                Toast.makeText(LoginActivity.this,
                               login.getText().toString() + " / " + senha.getText().toString() + " / " + numeroAuxRadio,
                                Toast.LENGTH_LONG).show();
            }
        });


        //Botao Esqueceu a senha
        botaoEsqueceuSenha = (Button) findViewById(R.id.login_botaoEsqueceuSenha_button);
        botaoEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent vaiParaEsqueceuSenha = new Intent(LoginActivity.this, EsqueceuSenhaActivity.class);
                //startActivity(vaiParaEsqueceuSenha);
                //Toast.makeText(LoginActivity.this, "Clicou Botao Esqueceu a senha", Toast.LENGTH_LONG).show();
                VagaDAO x = new VagaDAO(LoginActivity.this);
                x.insereVagasAutomaticoAoCriarBanco();
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


}
