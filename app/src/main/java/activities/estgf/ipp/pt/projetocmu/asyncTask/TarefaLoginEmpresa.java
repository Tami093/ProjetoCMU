package activities.estgf.ipp.pt.projetocmu.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.security.PrivateKey;

/**
 * Created by Tamires on 21/01/2018.
 */

public class TarefaLoginEmpresa extends AsyncTask<String,String,String> {
    private Context context;
    private TextView msgProgresso;

    public TarefaLoginEmpresa(Context context, TextView msgProgresso) {
        this.context = context;
        this.msgProgresso = msgProgresso;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msgProgresso.setText("");
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected String doInBackground(String... msg) {
        msgProgresso.setText("Verificando dados");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
