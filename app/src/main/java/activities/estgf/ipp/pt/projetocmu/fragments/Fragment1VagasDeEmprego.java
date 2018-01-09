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
import activities.estgf.ipp.pt.projetocmu.dao.VagaDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class Fragment1VagasDeEmprego extends Fragment{

    private ListView listaVagas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewFragVaga = inflater.inflate(R.layout.fragment1_vaga_de_emprego, container, false);
        listaVagas = (ListView) viewFragVaga.findViewById(R.id.fragment1_listaEmpregos_listView);

        //Cria um monte de vagas
        VagaDAO dao = new VagaDAO(container.getContext());
        List<Vaga> vagas = dao.buscaVagas();

        //Infla o layout com as vagas
        VagasEmpregoAdapter adapterEmprego  = new VagasEmpregoAdapter(container.getContext() ,vagas);
        listaVagas.setAdapter(adapterEmprego);

        if(container == null){
            return null;
        }

        return viewFragVaga;
    }

}
