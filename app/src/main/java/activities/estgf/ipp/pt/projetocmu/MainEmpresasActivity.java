package activities.estgf.ipp.pt.projetocmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import activities.estgf.ipp.pt.projetocmu.dao.EmpresaDAO;

public class MainEmpresasActivity extends AppCompatActivity {

    private Button botaoTestar;
    private EmpresaDAO empresaDAo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empresas);


        botaoTestar = (Button) findViewById(R.id.botao_testarBanco);
        botaoTestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empresaDAo = new EmpresaDAO(MainEmpresasActivity.this);
                empresaDAo.TestaDadosCadastrados();
            }
        });
    }
}
