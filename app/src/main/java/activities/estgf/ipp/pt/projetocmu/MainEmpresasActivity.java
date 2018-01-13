package activities.estgf.ipp.pt.projetocmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import activities.estgf.ipp.pt.projetocmu.adapter.VagasEmpregosDaEmpresa;
import activities.estgf.ipp.pt.projetocmu.dao.EmpresaDAO;
import activities.estgf.ipp.pt.projetocmu.dao.VagaDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class MainEmpresasActivity extends AppCompatActivity {

    private Button botaoTestar;
    private EmpresaDAO empresaDAo;
    private ListView listaVagasDaEmpresa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empresas);


        listaVagasDaEmpresa = (ListView) findViewById(R.id.mainEmpresas_listaDeVagasDaEmpresa_listview);

        botaoTestar = (Button) findViewById(R.id.botao_testarBanco);
        botaoTestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empresaDAo = new EmpresaDAO(MainEmpresasActivity.this);
                empresaDAo.TestaDadosCadastrados();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaListaVagas();
    }

    private void carregaListaVagas() {
        VagaDAO vagaDAO = new VagaDAO(this);
        List<Vaga> vagas = vagaDAO.buscaVagas();

        //ArrayAdapter<Aluno> arrAdapter = new ArrayAdapter<Aluno>(this, R.layout.list_item, alunos);
        //É o adapter
        VagasEmpregosDaEmpresa adapter = new VagasEmpregosDaEmpresa(vagas,this);
        listaVagasDaEmpresa.setAdapter(adapter);
    }
}
