package activities.estgf.ipp.pt.projetocmu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
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
                SmsManager smsRedefinicao = SmsManager.getDefault();

                if  (email.getText().toString().equals("")){
                    Toast.makeText(EsqueceuSenhaActivity.this,"Campo vazio",Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain"); // send email as plain text
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "crisoliveira.93@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Teste1");
                intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                startActivity(Intent.createChooser(intent, ""));
                Toast.makeText(EsqueceuSenhaActivity.this,"Email enviado ",Toast.LENGTH_LONG).show();

            }
        });






    }
}
