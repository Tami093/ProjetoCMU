package activities.estgf.ipp.pt.projetocmu;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class CurriculoAlunoActivity extends AppCompatActivity {

    private TextView dataAniversario;
    private EditText nome, email, endereco, telefone;
    private Button botaoSalvar;

    private DatePickerDialog.OnDateSetListener dataAniversarioListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculo_aluno);


        dataDeAniversario();

    }



    //Parte da data de Aniversario
    public void dataDeAniversario(){
        dataAniversario = (TextView) findViewById(R.id.curriculo_dataAniversario_textView);
        dataAniversario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendario = Calendar.getInstance();
                int ano = calendario.get(Calendar.YEAR);
                int mes = calendario.get(Calendar.MONTH);
                int dia = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CurriculoAlunoActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dataAniversarioListener,
                        ano,mes,dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dataAniversarioListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                mes++;
                String dataEscolhida = dia + "/" + mes + "/" + ano;
                dataAniversario.setText(dataEscolhida);
            }
        };
    }
}
