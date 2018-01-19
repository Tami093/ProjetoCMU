package activities.estgf.ipp.pt.projetocmu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import activities.estgf.ipp.pt.projetocmu.adapter.VagasEmpregosDaEmpresa;
import activities.estgf.ipp.pt.projetocmu.dao.VagaDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class MainEmpresasActivity extends AppCompatActivity {

    private Intent intent;
    private long idDaEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empresas);
        intent = getIntent();
        idDaEmpresa = intent.getLongExtra("idDaEmpresa",0);


        //http://blog.alura.com.br/criando-listas-com-recyclerview/  (Pagina sobre recycleView)
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mainEmpresas_listaDeVagasDaEmpresa_recyclerView);

        List<Vaga> vagas = carregaListaVagas(idDaEmpresa);

        recyclerView.setAdapter(new VagasEmpregosDaEmpresa(vagas, this));
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

    }

    private List<Vaga> carregaListaVagas(long idEmpresa) {
        VagaDAO vagaDAO = new VagaDAO(this);
        List<Vaga> vagas = vagaDAO.buscaVagasDaEmpresa(idEmpresa);

        return vagas;
    }
}
