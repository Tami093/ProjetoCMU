package activities.estgf.ipp.pt.projetocmu.fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

                //Toast.makeText(getContext(), "Clicou no Aluno : " + aluno, Toast.LENGTH_SHORT).show();
                AlunosCandidatosActivity alunosCandidatosActivity = (AlunosCandidatosActivity) getActivity();
                alunosCandidatosActivity.selecionaAluno(curriculoDao.existeCurriculo(aluno.getIdAluno()));

            }
        });

        registerForContextMenu(listaAlunos);

        return view;
    }

    //https://pt.stackoverflow.com/questions/95433/m%C3%A9todo-oncreatecontextmenu-de-um-fragment-chama-o-m%C3%A9todo-oncontextitemselected-d (Site que achei)
    //Valores que irao aparecer ao segurar no aluno
    //Nao esquecer de chamar a funcao registerForContextMenu no onCreate, pois se n tiver nao ira apracer o menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, 0, 0, "Ligar Para o Aluno");
        menu.add(1, 1, 1, "Enviar SMS para o Aluno");
        menu.add(1, 2, 2, "Enviar um email para o Aluno");
    }

    //Logica para funcionar os itens selecionados em onCreateCOntextMenu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

        CurriculoDao curriculoDao = new CurriculoDao(getContext());
        final Curriculo curriculo = curriculoDao.existeCurriculo(aluno.getIdAluno());

        switch (item.getItemId()) {
            case 0:
                //Toast.makeText(getContext(),"Tocou no Ligar " + aluno.getNome(),Toast.LENGTH_LONG).show();

                //Ligar
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
            case 1:
                //Toast.makeText(getContext(),"Tocou no SMS",Toast.LENGTH_LONG).show();

                //Enviar SMS
                Intent intetntSMS = new Intent(Intent.ACTION_VIEW);
                intetntSMS.setData(Uri.parse("sms:" + curriculo.getTelefone()));
                item.setIntent(intetntSMS);
                startActivity(intetntSMS);

                break;
            case 2:
                //Toast.makeText(getContext(),"Tocou no Email",Toast.LENGTH_LONG).show();

                //Enviar Email
                Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
                intentEmail.setData(Uri.parse("mailto:")); // only email apps should handle this
                intentEmail.putExtra(Intent.EXTRA_EMAIL, curriculo.getEmail());
                startActivity(intentEmail);
                break;
            default:
                return false;

        }
        return true;
    }

}
