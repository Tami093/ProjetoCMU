package activities.estgf.ipp.pt.projetocmu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import activities.estgf.ipp.pt.projetocmu.R;
import activities.estgf.ipp.pt.projetocmu.dao.CurriculoDao;
import activities.estgf.ipp.pt.projetocmu.modelo.Aluno;
import activities.estgf.ipp.pt.projetocmu.modelo.Curriculo;

public class AlunosCandidatosAdapter extends BaseAdapter {


    private final List<Aluno> alunos;
    private final Context context;

    public AlunosCandidatosAdapter(List<Aluno> alunos, Context context) {
        this.alunos = alunos;
        this.context = context;
    }

    @Override
    public int getCount() { return alunos.size(); }

    @Override
    public Object getItem(int position) { return alunos.get(position); }

    @Override
    public long getItemId(int position) { return alunos.get(position).getIdAluno(); }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        Aluno aluno = alunos.get(position);

        CurriculoDao curriculoDao = new CurriculoDao(context);
        Curriculo curriculo = new Curriculo();

        curriculo = curriculoDao.existeCurriculo(aluno.getIdAluno());

        LayoutInflater inflater = LayoutInflater.from(context);

        View viewInflate = view;
        if(viewInflate == null){
            viewInflate = inflater.inflate(R.layout.list_item_alunos_para_vaga, viewGroup , false); //Esse viewGrupo é a propria view, coloca falso para nao colocar 2 vezes ela.
        }

        TextView nomeAluno = (TextView) viewInflate.findViewById(R.id.listItem_nomeDoAlunoCandidato_textView);
        nomeAluno.setText(aluno.getNome());

        TextView emailAluno = (TextView) viewInflate.findViewById(R.id.listItem_emailAlunoCandidato_textView);
        emailAluno.setText(aluno.getEmail());

        TextView telefoneAluno = (TextView) viewInflate.findViewById(R.id.listItem_telefoneAlunoCandidato_textView);
        telefoneAluno.setText(curriculo.getTelefone());

        return viewInflate;
    }
}













