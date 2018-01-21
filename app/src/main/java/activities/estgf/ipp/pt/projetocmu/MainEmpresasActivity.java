package activities.estgf.ipp.pt.projetocmu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import activities.estgf.ipp.pt.projetocmu.adapter.VagasEmpregosDaEmpresa;
import activities.estgf.ipp.pt.projetocmu.dao.VagaDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class MainEmpresasActivity extends AppCompatActivity {

    private Intent intent;
    private long idDaEmpresa;
    private String nomeEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empresas);
        intent = getIntent();
        idDaEmpresa = intent.getLongExtra("idDaEmpresa",0);
        nomeEmpresa = intent.getStringExtra("nomeEmpresa");

        /*  Pagina sobre recycleView
            http://blog.alura.com.br/criando-listas-com-recyclerview/
        */
        carregaListaVagas(idDaEmpresa);

    }

    private void carregaListaVagas(long idEmpresa) {
        VagaDAO vagaDAO = new VagaDAO(this);
        List<Vaga> vagas = vagaDAO.buscaVagasDaEmpresa(idEmpresa);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mainEmpresas_listaDeVagasDaEmpresa_recyclerView);

        recyclerView.setAdapter(new VagasEmpregosDaEmpresa(vagas, this));
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
    }


    @Override
    protected void onResume() {
        super.onResume();
        carregaListaVagas(idDaEmpresa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vagas_emresas_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuVagasEmpresas_criarNovaVaga_item:
                Intent vaiParaCriarVaga = new Intent(this, NovaVagaActivity.class);
                vaiParaCriarVaga.putExtra("idEmpresa", idDaEmpresa);
                vaiParaCriarVaga.putExtra("nomeEmpresa", nomeEmpresa);
                startActivity(vaiParaCriarVaga);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
