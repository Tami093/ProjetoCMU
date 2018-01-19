package activities.estgf.ipp.pt.projetocmu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.AlunosCandidatosActivity;
import activities.estgf.ipp.pt.projetocmu.R;
import activities.estgf.ipp.pt.projetocmu.dao.CandidataDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class VagasEmpregosDaEmpresa extends RecyclerView.Adapter {

    private final List<Vaga> vagas;
    private final Context context;

    public VagasEmpregosDaEmpresa(List<Vaga> vagas, Context context) {
        this.vagas = vagas;
        this.context = context;
    }

    //Inflar o layout com o item da vaga nesse caso
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_vagas_empregos_da_empresa, parent, false);
        MeuViewHolder holder = new MeuViewHolder(view);
        return holder;
    }

    //Logica para setar os TextViews e a logica do Botao
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MeuViewHolder holder = (MeuViewHolder) viewHolder;

        final Vaga vaga = vagas.get(position);
        CandidataDAO candidataDAO = new CandidataDAO(context);

        holder.nomeVaga.setText(vaga.getNomeVaga());
        holder.localDoTrabalho.setText(vaga.getLocalTrabalho());
        holder.quantidadeSalario.setText(vaga.getSalario());
        holder.qtdPessoasParaVaga.setText("Candidatos Para Vaga: " + candidataDAO.quantidadeDeCandidatos(String.valueOf(vaga.getId())));
        holder.botaoVerCandidatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CandidataDAO candidataDAO = new CandidataDAO(context);
                List<Long> resultadosIdsAlunos = new ArrayList<Long>();
                resultadosIdsAlunos = candidataDAO.todosAlunosParaAquelaVaga(vaga.getId());
                long ids[] = new long [resultadosIdsAlunos.size()];

                for (int i = 0 ; i < ids.length ; i++){
                    ids[i] = resultadosIdsAlunos.get(i).longValue();
                    System.out.println(ids[i]);
                }

                Intent vaiParaListaDeAlunos = new Intent( context, AlunosCandidatosActivity.class);
                vaiParaListaDeAlunos.putExtra("listaIdsAlunos", ids);
                context.startActivity(vaiParaListaDeAlunos);
                //Toast.makeText(context, "TOCOU NO BOTAO", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Tamanho da lista de vagas
    @Override
    public int getItemCount() { return vagas.size(); }

    //É o meu Holder
    public class MeuViewHolder extends RecyclerView.ViewHolder {
        final TextView nomeVaga ,localDoTrabalho, quantidadeSalario ,qtdPessoasParaVaga;
        final Button botaoVerCandidatos;

        public MeuViewHolder(View itemView) {
            super(itemView);
            nomeVaga = (TextView) itemView.findViewById(R.id.listItemVagasEmpregoDaEmpresa_nomeVaga_textView);
            localDoTrabalho = (TextView) itemView.findViewById(R.id.listItemVagasEmpregoDaEmpresa_localTrabalho_textView);
            quantidadeSalario = (TextView) itemView.findViewById(R.id.listItemVagasEmpregoDaEmpresa_valorSalario_textView);
            qtdPessoasParaVaga = (TextView) itemView.findViewById(R.id.listItemVagasEmpregoDaEmpresa_qtdPessoasParaVaga_textView);
            botaoVerCandidatos = (Button) itemView.findViewById(R.id.listItemVagasEmpregoDaEmpresa_verificarParticipantes_button);
        }
    }
}
