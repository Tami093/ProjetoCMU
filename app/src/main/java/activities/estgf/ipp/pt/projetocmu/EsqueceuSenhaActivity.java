package activities.estgf.ipp.pt.projetocmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EsqueceuSenhaActivity extends AppCompatActivity {

    private EditText email;
    private Button botaoEsqueciSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        email = (EditText) findViewById(R.id.esqueceuSenha_digitarEmail_editText);
        botaoEsqueciSenha = (Button) findViewById(R.id.esqueceuSenha_botaoEnviar_button);
        botaoEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("") || email.getText().toString().equals(null)){
                    Toast.makeText(EsqueceuSenhaActivity.this, "Digite o E-mail por favor", Toast.LENGTH_SHORT);
                    return;
                }

                Toast.makeText(EsqueceuSenhaActivity.this, "Email: " + email , Toast.LENGTH_SHORT);
            }
        });


    }
}
