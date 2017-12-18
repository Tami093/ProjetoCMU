package activities.estgf.ipp.pt.projetocmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome, email, nif, senha, confirmaSenha;
    private Button botaoCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText) findViewById(R.id.cadastro_nome_editText);
        email = (EditText) findViewById(R.id.cadastro_email_editText);
        nif = (EditText) findViewById(R.id.cadastro_nif_editText);
        senha = (EditText) findViewById(R.id.cadastro_senha_editText);
        confirmaSenha = (EditText) findViewById(R.id.cadastro_confirmaSenha_editText);
        botaoCadastrar = (Button) findViewById(R.id.cadastro_botaoCadastro_button);

        
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nome.getText().toString().equals("") || nome.getText().toString().equals(null)
                   ||email.getText().toString().equals("") || email.getText().toString().equals(null)
                   ||nif.getText().toString().equals("") || nif.getText().toString().equals(null)
                   ||senha.getText().toString().equals("") || senha.getText().toString().equals(null)
                   ||confirmaSenha.getText().toString().equals("") || confirmaSenha.getText().toString().equals(null)) {
                    Toast.makeText(CadastroActivity.this, "Entre Com Valores Nos Campos Por Favor", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Verificacao se as senhas sao iguais
                if(senha.getText().toString().equals(confirmaSenha.getText().toString())){
                    Toast.makeText(CadastroActivity.this, senha.getText().toString() + " -Iguais- " + confirmaSenha.getText().toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CadastroActivity.this, senha.getText().toString() + " /Diferentes/ " + confirmaSenha.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
