package activities.estgf.ipp.pt.projetocmu;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import activities.estgf.ipp.pt.projetocmu.dao.AlunoDAO;

/**
 * Created by Tamires on 16/01/2018.
 */

public class BarraDeProgressoLogin extends AsyncTask<String,String,String>{
    private ProgressBar login;
    Context context;

    public BarraDeProgressoLogin(ProgressBar login) {
        this.login = login;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        login.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        login.setVisibility(View.GONE);
    }


    @Override
    protected String doInBackground(String... params) {
        AlunoDAO alunoDAO = new AlunoDAO(context);
        String idAluno;
        idAluno = String.valueOf(alunoDAO.pegaIdAluno(params[0],params[1])) ;
        return idAluno;
    }
}
