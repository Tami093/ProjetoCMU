package activities.estgf.ipp.pt.projetocmu.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import activities.estgf.ipp.pt.projetocmu.R;
import activities.estgf.ipp.pt.projetocmu.modelo.Curriculo;

public class Fragmento2AlunosCandidatos extends Fragment{


    private TextView nomeAluno, aniversarioAluno, sexoAluno, telefoneAluno, emailAluno, enderecoAluno;
    private TextView objetivoALuno, formacaoAluno, empresaTrabalhouAluno, cargoDoAluno, duracaoTrabalhoAluno , idioma1, idioma2;
    private Curriculo curriculo;

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
            curriculo = (Curriculo) parametros.getSerializable("curriculo");
            pupolarCamposCurriculo(curriculo);
        }

        if(container == null){ return null; }


        //Essa funcao faz com que o fragment tenha Menu Na TollBar
        setHasOptionsMenu(true);

        return view;
    }



    //Criacao do menu lateral da tollbar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment2_alunoscandidatos, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    //Logica ao selecionar um valor do menu na TollBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.fragment2_ligarAlunoCandidato:
                //Ligacao
                if(ActivityCompat.checkSelfPermission(getContext() , Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[] {Manifest.permission.CALL_PHONE},
                            123);  // requestCode é para diferenciar em uma classe especifica oque o android chama. Deixa pra la
                }else {
                    Intent intentLigar = new Intent(Intent.ACTION_DIAL); //Action_CAll -- Liga direto, nao vai para o telefone
                    intentLigar.setData(Uri.parse("tel:"+curriculo.getTelefone()));
                    startActivity(intentLigar);
                }
                break;

            case R.id.fragment2_enviarSMSAlunoCandidato:
                //Enviar SMS
                Intent intetntSMS = new Intent(Intent.ACTION_VIEW);
                intetntSMS.setData(Uri.parse("sms:" + curriculo.getTelefone()));
                item.setIntent(intetntSMS);
                startActivity(intetntSMS);
                break;

            case R.id.fragment2_enviarEmailAlunoCandidato:
                //Enviar Email
                Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
                intentEmail.setData(Uri.parse("mailto:")); // only email apps should handle this
                intentEmail.putExtra(Intent.EXTRA_EMAIL, curriculo.getEmail());
                startActivity(intentEmail);
                break;

            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
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
