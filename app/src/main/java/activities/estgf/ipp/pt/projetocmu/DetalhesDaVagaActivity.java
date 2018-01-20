package activities.estgf.ipp.pt.projetocmu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import activities.estgf.ipp.pt.projetocmu.dao.CandidataDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Candidata;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class DetalhesDaVagaActivity extends AppCompatActivity {

    private TextView nomeVaga, nomeEmpresa, localTrabalho, tipoDaVaga, salario;
    private Button botaoCandidatar;
    private Intent intentVagasDeEmprego;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_da_vaga);

        intentVagasDeEmprego = getIntent();
        final Vaga vaga = (Vaga) intentVagasDeEmprego.getSerializableExtra("vaga");
        final long idAluno = (long) intentVagasDeEmprego.getLongExtra("idDoAluno",0);

        nomeVaga      = (TextView) findViewById(R.id.detalheVaga_nomeVaga_textView);
        nomeEmpresa   = (TextView) findViewById(R.id.detalheVaga_nomeEmpresa_textView);
        localTrabalho = (TextView) findViewById(R.id.detalheVaga_localTrabalho_textView);
        tipoDaVaga    = (TextView) findViewById(R.id.detalheVaga_tipoVaga_textView);
        salario       = (TextView) findViewById(R.id.detalheVaga_salario_textView);

        preencheCampos(vaga);

        botaoCandidatar = (Button)findViewById(R.id.detalheVaga_botaoCandidatar_button);
        botaoCandidatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Candidata candidata = new Candidata();
                candidata.setIdVaga(vaga.getId());
                candidata.setIdAluno(idAluno);

                CandidataDAO candidataDAO = new CandidataDAO(DetalhesDaVagaActivity.this);
                candidataDAO.insereCandidatura(candidata);

                Toast.makeText(DetalhesDaVagaActivity.this,"Candidato com sucesso", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }



    public void preencheCampos (Vaga vaga){
        nomeVaga.setText(vaga.getNomeVaga());
        nomeEmpresa.setText(vaga.getNomeEmpresa());
        localTrabalho.setText(vaga.getLocalTrabalho());
        tipoDaVaga.setText(vaga.getTipoVaga());
        salario.setText(vaga.getSalario());
    }
}
