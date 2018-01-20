package activities.estgf.ipp.pt.projetocmu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import activities.estgf.ipp.pt.projetocmu.dao.EmpresaDAO;
import activities.estgf.ipp.pt.projetocmu.dao.VagaDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class NovaVagaActivity extends AppCompatActivity {

    private Intent intentMainEmpresas;
    private TextView nomeVaga, tipoVaga, localTrabalho, salario;
    private Button botaoCriarVaga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_vaga);

        intentMainEmpresas = getIntent();
        final long idEmpresa = intentMainEmpresas.getLongExtra("idEmpresa",0);
        final String nomeEmpresa = intentMainEmpresas.getStringExtra("nomeEmpresa");

        nomeVaga      = (TextView) findViewById(R.id.novaVaga_nomeVaga_editText);
        tipoVaga      = (TextView) findViewById(R.id.novaVaga_tipoVaga_editText);
        localTrabalho = (TextView) findViewById(R.id.novaVaga_localTrabalho_editText);
        salario       = (TextView) findViewById(R.id.novaVaga_salario_editText);

        botaoCriarVaga = (Button) findViewById(R.id.novaVaga_criarVaga_button);
        botaoCriarVaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((nomeVaga.getText().toString().equals("") || nomeVaga.getText().toString().equals(null))
                    ||tipoVaga.getText().toString().equals("") || tipoVaga.getText().toString().equals(null)
                    ||localTrabalho.getText().toString().equals("") || localTrabalho.getText().toString().equals(null)
                    ||salario.getText().toString().equals("") || salario.getText().toString().equals(null)){

                    Toast.makeText(NovaVagaActivity.this, "Por favor, Preencher Todos os Campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    VagaDAO vagaDAO = new VagaDAO(NovaVagaActivity.this);
                    Vaga vaga = populaVaga(idEmpresa, nomeEmpresa);

                    vagaDAO.insereVaga(vaga);

                    Toast.makeText(NovaVagaActivity.this, "Vaga Criada Com Sucesso", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });

    }

    private Vaga populaVaga(long idEmpresa, String nomeEmpresa){
        Vaga vaga = new Vaga();

        vaga.setNomeVaga(nomeVaga.getText().toString());
        vaga.setTipoVaga(tipoVaga.getText().toString());
        vaga.setLocalTrabalho(localTrabalho.getText().toString());
        vaga.setSalario(salario.getText().toString());
        vaga.setIdEmpresa(idEmpresa);
        vaga.setNomeEmpresa(nomeEmpresa);
        vaga.setVagaAtiva("true");

        return vaga;
    }
}
