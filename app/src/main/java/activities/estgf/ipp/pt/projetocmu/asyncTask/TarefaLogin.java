package activities.estgf.ipp.pt.projetocmu.asyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

import activities.estgf.ipp.pt.projetocmu.dao.AlunoDAO;

/**
 * Created by Tamires on 16/01/2018.
 */

public class TarefaLogin extends AsyncTask<String,String,String>{
    private EditText logiin,senha;
    private TextView msgProgresso;
    Context context;

    public TarefaLogin(TextView msgProgresso, EditText logiin, EditText senha, Context context) {
        this.msgProgresso= msgProgresso;
        this.logiin = logiin;
        this.senha = senha;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
      //  result= "Verificando informações";
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      //  msgProgresso.setText(result);
        result= "";
        msgProgresso.setText(result);

    }

    @Override
    protected String doInBackground(String... params) {
        AlunoDAO alunoDAO = new AlunoDAO(context);
        String idAluno;

        msgProgresso.setText("Verificando informações ...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        idAluno = String.valueOf(alunoDAO.pegaIdAluno(logiin.getText().toString(),senha.getText().toString()));
        return idAluno;
    }
}
