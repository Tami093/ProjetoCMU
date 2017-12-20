package activities.estgf.ipp.pt.projetocmu.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.R;
import activities.estgf.ipp.pt.projetocmu.adapter.VagasEmpregoAdapter;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class Fragment1VagasDeEmprego extends Fragment{

    private ListView listaVagas;
    private List<Vaga> vagas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewFragVaga = inflater.inflate(R.layout.fragment1_vaga_de_emprego, container, false);
        listaVagas = (ListView) viewFragVaga.findViewById(R.id.fragment1_listaEmpregos_listView);

        //Cria um monte de vagas
        testeVagas();

        //Infla o layout com as vagas
        VagasEmpregoAdapter adapterEmprego  = new VagasEmpregoAdapter(container.getContext() ,vagas);
        listaVagas.setAdapter(adapterEmprego);

        if(container == null){
            return null;
        }

        return viewFragVaga;
    }




    //Criar 5 vagas para teste no app
    private void testeVagas() {
        vagas = new ArrayList<Vaga>();
        Vaga v1 = new Vaga();
        Vaga v2 = new Vaga();
        Vaga v3 = new Vaga();
        Vaga v4 = new Vaga();
        Vaga v5 = new Vaga();

        v1.setId(1);
        v1.setLocalTrabalho("Rua Joao Nogueira");
        v1.setNomeEmpresa("Continente ");
        v1.setSalario("1000 Euros");
        v1.setNomeVaga("Vaga Para Caixa");
        v1.setTipoVaga("Estagiario");
        v1.setVagaAtiva(true);
        vagas.add(v1);

        v2.setId(2);
        v2.setLocalTrabalho("Rua da Santa Quiteria");
        v2.setNomeEmpresa("Quiosque legal");
        v2.setSalario("753 Euros");
        v2.setNomeVaga("Vaga Para Garconete");
        v2.setTipoVaga("Efetivo");
        v2.setVagaAtiva(true);
        vagas.add(v2);

        v3.setId(3);
        v3.setLocalTrabalho("Rua do Estadio");
        v3.setNomeEmpresa("Campo Futebol Felgueiras");
        v3.setSalario("1254 Euros");
        v3.setNomeVaga("Vaga para cortador de grama");
        v3.setTipoVaga("Efetivado");
        v3.setVagaAtiva(true);
        vagas.add(v3);

        v4.setId(4);
        v4.setLocalTrabalho("ESTG Faculdade");
        v4.setNomeEmpresa("ESTG");
        v4.setSalario("850 Euros");
        v4.setNomeVaga("Vaga Para TI");
        v4.setTipoVaga("Estagiario");
        v4.setVagaAtiva(true);
        vagas.add(v4);

        v5.setId(5);
        v5.setLocalTrabalho("Rua de Sao Jose");
        v5.setNomeEmpresa("Pastelarias Fixe");
        v5.setSalario("1739 Euros");
        v5.setNomeVaga("Pasteleiro");
        v5.setTipoVaga("Efetivado");
        v5.setVagaAtiva(true);
        vagas.add(v5);
    }
}
