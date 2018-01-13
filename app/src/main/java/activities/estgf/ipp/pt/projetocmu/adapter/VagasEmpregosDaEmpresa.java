package activities.estgf.ipp.pt.projetocmu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import activities.estgf.ipp.pt.projetocmu.R;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class VagasEmpregosDaEmpresa extends BaseAdapter {

    private final List<Vaga> vagas;
    private final Context context;

    public VagasEmpregosDaEmpresa(List<Vaga> vagas, Context context) {
        this.vagas = vagas;
        this.context = context;
    }

    @Override
    public int getCount() { return vagas.size(); }
    @Override
    public Object getItem(int position) { return vagas.get(position); }
    @Override
    public long getItemId(int position) { return vagas.get(position).getId(); }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Vaga vaga = vagas.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View viewInflate = view;
        if(viewInflate == null){
            viewInflate = inflater.inflate(R.layout.list_item_vagas_empregos_da_empresa, viewGroup, false); //Esse viewGrupo é a propria view, coloca falso para nao colocar 2 vezes ela.
        }

        TextView nomeVaga = (TextView) viewInflate.findViewById(R.id.listItemVagasEmpregoDaEmpresa_nomeVaga_textView);
        nomeVaga.setText(vaga.getNomeVaga());

        TextView localDoTrabalho = (TextView) viewInflate.findViewById(R.id.listItemVagasEmpregoDaEmpresa_localTrabalho_textView);
        localDoTrabalho.setText(vaga.getLocalTrabalho());

        TextView quantidadeSalario = (TextView) viewInflate.findViewById(R.id.listItemVagasEmpregoDaEmpresa_valorSalario_textView);
        quantidadeSalario.setText(vaga.getSalario());


        return viewInflate;
    }
}
