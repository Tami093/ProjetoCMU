package activities.estgf.ipp.pt.projetocmu;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import activities.estgf.ipp.pt.projetocmu.dao.AlunoDAO;
import activities.estgf.ipp.pt.projetocmu.dao.CandidataDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Aluno;
import activities.estgf.ipp.pt.projetocmu.modelo.Candidata;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class DetalhesDaVagaActivity extends AppCompatActivity {

    private TextView nomeVaga, nomeEmpresa, localTrabalho, tipoDaVaga, salario;
    private Button botaoCandidatar;
    private Intent intentVagasDeEmprego;
    private int notificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_da_vaga);

        intentVagasDeEmprego = getIntent();
        final Vaga vaga = (Vaga) intentVagasDeEmprego.getSerializableExtra("vaga");
        final long idAluno = (long) intentVagasDeEmprego.getLongExtra("idDoAluno",0);

        AlunoDAO alunoDAO = new AlunoDAO(this);
        final Aluno aluno = alunoDAO.pegarUnicoAluno(idAluno);

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

                enviarNotificacao(vaga.getNomeVaga(), aluno.getNome());

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

    public void enviarNotificacao(String nomeVaga, String nomeAluno){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_work_black_24dp);
        mBuilder.setContentTitle("Novo Aluno interessado na Vaga : " + nomeVaga);
        mBuilder.setContentText("O aluno "+ nomeAluno+ " Candidatou-se a vaga.");
        mBuilder.setWhen(Calendar.getInstance().getTimeInMillis() - 3600000);
        // mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);

        notificationId = 001;

        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(notificationId, mBuilder.build());
    }
}
