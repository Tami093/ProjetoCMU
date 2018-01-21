package activities.estgf.ipp.pt.projetocmu.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import activities.estgf.ipp.pt.projetocmu.DetalhesDaVagaActivity;
import activities.estgf.ipp.pt.projetocmu.R;
import activities.estgf.ipp.pt.projetocmu.dao.CandidataDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Candidata;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;


public class VagasEmpregoAdapter extends BaseAdapter {

    private final List<Vaga> vagas;
    private final Context context;
    private final Long idAluno;

    public VagasEmpregoAdapter(Context context, List<Vaga> vagas, Long idAluno) {
        this.vagas = vagas;
        this.context = context;
        this.idAluno = idAluno;
    }

    @Override
    public int getCount() { return vagas.size(); }

    @Override
    public Object getItem(int position) { return vagas.get(position); }

    @Override
    public long getItemId(int position) { return vagas.get(position).getId(); }

    @Override
    public View getView(int position, View views, ViewGroup viewGroup) {
        boolean botaoCandidatarse = false;
        final Vaga vaga = vagas.get(position);

        CandidataDAO candidataDAO = new CandidataDAO(context);

        LayoutInflater inflater = LayoutInflater.from(context);

        View viewInflate = views;
        if(viewInflate == null){
            viewInflate = inflater.inflate(R.layout.list_item_vaga_emprego, viewGroup , false); //Esse viewGrupo é a propria view, coloca falso para nao colocar 2 vezes ela.
        }

        TextView nomeVaga = (TextView) viewInflate.findViewById(R.id.listItem_nomeDaVagaEmprego_textview);
        nomeVaga.setText(vaga.getNomeVaga());

        TextView nomeEmpresa = (TextView) viewInflate.findViewById(R.id.listItem_nomeEmpresa_textView);
        nomeEmpresa.setText(vaga.getNomeEmpresa());

        TextView localidadeTrabalho = (TextView) viewInflate.findViewById(R.id.listItem_localDoTrabalho_textView);
        localidadeTrabalho.setText(vaga.getLocalTrabalho());

        TextView quantidadeSalario  = (TextView) viewInflate.findViewById(R.id.listItem_quantidadeSalario_textView);
        quantidadeSalario.setText(vaga.getSalario());

        Button botaoCandidatar = (Button) viewInflate.findViewById(R.id.listItem_botaoCandidatar_button);
//        if(candidataDAO.alunoCandidatoAVaga(vaga.getId(), idAluno)){
        botaoCandidatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaDetalhesVaga = new Intent(context, DetalhesDaVagaActivity.class);
                vaiParaDetalhesVaga.putExtra("vaga", vaga);
                vaiParaDetalhesVaga.putExtra("idDoAluno", idAluno);
                context.startActivity(vaiParaDetalhesVaga);
            }
        });
        return viewInflate;
    }


}
