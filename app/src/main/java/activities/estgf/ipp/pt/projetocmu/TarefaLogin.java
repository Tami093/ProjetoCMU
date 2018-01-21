package activities.estgf.ipp.pt.projetocmu;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.IOException;

import activities.estgf.ipp.pt.projetocmu.dao.AlunoDAO;

/**
 * Created by Tamires on 16/01/2018.
 */

public class TarefaLogin extends AsyncTask<String,String,String>{
    ProgressBar loginProgressBar;
    private EditText logiin,senha;
    Context context;

    public TarefaLogin( ProgressBar loginProgressBar, EditText logiin, EditText senha, Context context) {
        this.loginProgressBar= loginProgressBar;
        this.logiin = logiin;
        this.senha = senha;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    //  loginProgressBar.setVisibility(View.VISIBLE);


    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
      //  loginProgressBar.setVisibility(View.GONE);

    }

    @Override
    protected String doInBackground(String... params) {
        AlunoDAO alunoDAO = new AlunoDAO(context);
        Log.i("AsyncTask","Pesquisando id aluno");
        String idAluno;
        System.out.println("teste"+logiin.getText().toString());
        System.out.println("Passou por aqui 3" + logiin.getText().toString() + senha.getText().toString());
        idAluno = String.valueOf(alunoDAO.pegaIdAluno(logiin.getText().toString(),senha.getText().toString()));

        return idAluno;
    }
}
