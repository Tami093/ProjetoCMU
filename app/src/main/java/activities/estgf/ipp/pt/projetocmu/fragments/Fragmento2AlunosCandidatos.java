package activities.estgf.ipp.pt.projetocmu.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import activities.estgf.ipp.pt.projetocmu.R;
import activities.estgf.ipp.pt.projetocmu.modelo.Curriculo;

public class Fragmento2AlunosCandidatos extends Fragment{


    private TextView nomeAluno, aniversarioAluno, sexoAluno, telefoneAluno, emailAluno, enderecoAluno;
    private TextView objetivoALuno, formacaoAluno, empresaTrabalhouAluno, cargoDoAluno, duracaoTrabalhoAluno , idioma1, idioma2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2_alunoscandidatos,container,false);

        nomeAluno             = (TextView) view.findViewById(R.id.alunosCandidatos_nomeAluno_textView);
        aniversarioAluno      = (TextView) view.findViewById(R.id.alunosCandidatos_dataAniversarioAluno_textView);
        sexoAluno             = (TextView) view.findViewById(R.id.alunosCandidatos_sexoAluno_textView);
        telefoneAluno         = (TextView) view.findViewById(R.id.alunosCandidatos_telefoneALuno_textView);
        emailAluno            = (TextView) view.findViewById(R.id.alunosCandidatos_emailAluno_textView);
        enderecoAluno         = (TextView) view.findViewById(R.id.alunosCandidatos_enderecoAluno_textView);
        objetivoALuno         = (TextView) view.findViewById(R.id.alunosCandidatos_objetivoAluno_textView);
        formacaoAluno         = (TextView) view.findViewById(R.id.alunosCandidatos_formacaoAluno_textView);
        empresaTrabalhouAluno = (TextView) view.findViewById(R.id.alunosCandidatos_nomeEmpresaTrablahouAluno_textView);
        cargoDoAluno          = (TextView) view.findViewById(R.id.alunosCandidatos_cargoAluno_textView);
        duracaoTrabalhoAluno  = (TextView) view.findViewById(R.id.alunosCandidatos_duracaoTabalhoAluno_textView);
        idioma1               = (TextView) view.findViewById(R.id.alunosCandidatos_idioma1Aluno_textView);
        idioma2               = (TextView) view.findViewById(R.id.alunosCandidatos_idioma2Aluno_textView);

        Bundle parametros = getArguments();
        if(parametros != null){
            Curriculo curriculo = (Curriculo) parametros.getSerializable("curriculo");
            pupolarCamposCurriculo(curriculo);
        }

        if(container == null){ return null; }

        return view;
    }



    public void pupolarCamposCurriculo(Curriculo curriculo){
        nomeAluno.setText(curriculo.getNome());
        aniversarioAluno.setText(curriculo.getDataNasc());
        sexoAluno.setText(curriculo.getSexo());
        telefoneAluno.setText(curriculo.getTelefone());
        emailAluno.setText(curriculo.getEmail());
        enderecoAluno.setText(curriculo.getEnderenco());
        objetivoALuno.setText(curriculo.getObejtivo());
        formacaoAluno.setText(curriculo.getCurso());
        empresaTrabalhouAluno.setText(curriculo.getEmpresa());
        cargoDoAluno.setText(curriculo.getCargo());
        duracaoTrabalhoAluno.setText(curriculo.getPerido());
        idioma1.setText(curriculo.getIdioma1());
        idioma2.setText(curriculo.getIdioma2());
    }



}
