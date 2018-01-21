package activities.estgf.ipp.pt.projetocmu;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import activities.estgf.ipp.pt.projetocmu.fragments.Fragmento2AlunosCandidatos;
import activities.estgf.ipp.pt.projetocmu.fragments.Framgent1AlunosCandidatos;
import activities.estgf.ipp.pt.projetocmu.modelo.Curriculo;

public class AlunosCandidatosActivity extends AppCompatActivity {

    private Intent intentVagasEmprego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos_candidatos);
        getSupportActionBar().setTitle("Alunos Candidatos");

        intentVagasEmprego = getIntent();
        long [] ids = intentVagasEmprego.getLongArrayExtra("listaIdsAlunos");

        Bundle bundle = new Bundle();
        Framgent1AlunosCandidatos fragobj = new Framgent1AlunosCandidatos();

        //Passando os ids para o Framgent1AlunosCandidatos
        bundle.putLongArray("listaIdsAlunos", ids);
        fragobj.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();

        tx.replace(R.id.frame_alunosCandidatosMain_framelayout, fragobj);
        tx.addToBackStack(null);
        tx.commit();
    }



    public void selecionaAluno (Curriculo curriculo){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();

        Fragmento2AlunosCandidatos detalhesFragment = new Fragmento2AlunosCandidatos();
        Bundle parametros = new Bundle();
        parametros.putSerializable("curriculo",curriculo);
        detalhesFragment.setArguments(parametros);

        tx.replace(R.id.frame_alunosCandidatosMain_framelayout, detalhesFragment);
        tx.addToBackStack(null);
        tx.commit();
    }
}
