package activities.estgf.ipp.pt.projetocmu.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.AlunosCandidatosActivity;
import activities.estgf.ipp.pt.projetocmu.R;
import activities.estgf.ipp.pt.projetocmu.adapter.AlunosCandidatosAdapter;
import activities.estgf.ipp.pt.projetocmu.dao.AlunoDAO;
import activities.estgf.ipp.pt.projetocmu.dao.CurriculoDao;
import activities.estgf.ipp.pt.projetocmu.modelo.Aluno;
import activities.estgf.ipp.pt.projetocmu.modelo.Curriculo;

public class Framgent1AlunosCandidatos extends Fragment {

    private ListView listaAlunos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_alunoscandidatos, container , false);
        listaAlunos = (ListView) view.findViewById(R.id.fragment1_alunosCandidatos_listview);

        AlunoDAO alunoDAO = new AlunoDAO(container.getContext());
        List<Aluno> alunos = new ArrayList<Aluno>();


        long [] ids = getArguments().getLongArray("listaIdsAlunos");
        //ids = savedInstanceState.getLongArray("listaIdsAlunos");
        for(int i = 0 ; i < ids.length; i++){
            Aluno aluno = alunoDAO.pegarUnicoAluno(ids[i]);
            alunos.add(aluno);
        }

        AlunosCandidatosAdapter adapterAlunos = new AlunosCandidatosAdapter(alunos, container.getContext());
        listaAlunos.setAdapter(adapterAlunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {  //i = Position / l = id
                Aluno aluno = (Aluno) parent.getItemAtPosition(i); //Pegando o item da prova selecionado
                CurriculoDao curriculoDao = new CurriculoDao(getContext());
                Curriculo curriculo = new Curriculo();

                Toast.makeText(getContext(), "Clicou no Aluno : " + aluno, Toast.LENGTH_SHORT).show();

                AlunosCandidatosActivity alunosCandidatosActivity = (AlunosCandidatosActivity) getActivity();
                alunosCandidatosActivity.selecionaAluno(curriculoDao.existeCurriculo(aluno.getIdAluno()));

            }
        });

        return view;
    }

}
