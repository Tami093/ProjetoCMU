package activities.estgf.ipp.pt.projetocmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import activities.estgf.ipp.pt.projetocmu.dao.EmpresaDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Empresa;
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome, email, nif, senha, confirmaSenha, telefone, endereco;
    private Button botaoCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText) findViewById(R.id.cadastro_nome_editText);
        email = (EditText) findViewById(R.id.cadastro_email_editText);
        nif = (EditText) findViewById(R.id.cadastro_nif_editText);
        telefone = (EditText) findViewById(R.id.cadastro_telefone_editText);
        endereco = (EditText) findViewById(R.id.cadastro_endereco_editText);
        senha = (EditText) findViewById(R.id.cadastro_senha_editText);
        confirmaSenha = (EditText) findViewById(R.id.cadastro_confirmaSenha_editText);
        botaoCadastrar = (Button) findViewById(R.id.cadastro_botaoCadastro_button);


        //mascara do campo telefone
        MaskEditTextChangedListener maskTEL = new MaskEditTextChangedListener("(###) ###-###-###", telefone);
        telefone.addTextChangedListener(maskTEL);
        
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verificacao se nenhum campo esta vazio
                if(nome.getText().toString().equals("") || nome.getText().toString().equals(null)
                   ||email.getText().toString().equals("") || email.getText().toString().equals(null)
                   ||nif.getText().toString().equals("") || nif.getText().toString().equals(null)
                   ||telefone.getText().toString().equals("") || telefone.getText().toString().equals(null)
                   ||endereco.getText().toString().equals("") || endereco.getText().toString().equals(null)
                   ||senha.getText().toString().equals("") || senha.getText().toString().equals(null)
                   ||confirmaSenha.getText().toString().equals("") || confirmaSenha.getText().toString().equals(null)) {
                    Toast.makeText(CadastroActivity.this, "Entre Com Valores Nos Campos Por Favor", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Verificacao se as senhas sao iguais e insere tambem se tudo estiver ok
                if(senha.getText().toString().equals(confirmaSenha.getText().toString())){
                    try {
                        EmpresaDAO empresaDao = new EmpresaDAO(CadastroActivity.this);
                        Empresa empresa = new Empresa(senha.getText().toString(), nome.getText().toString(), email.getText().toString(),
                                endereco.getText().toString(), telefone.getText().toString(), nif.getText().toString());

                        empresaDao.insere(empresa);

                        Toast.makeText(CadastroActivity.this, "Cadastro Feito Com Sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    }catch (android.database.SQLException e){
                        System.out.println(e);
                    }

                }else{
                    Toast.makeText(CadastroActivity.this, "Certifique que digitou a senha corretamente", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
