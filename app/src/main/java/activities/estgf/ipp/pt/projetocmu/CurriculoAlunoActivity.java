package activities.estgf.ipp.pt.projetocmu;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.service.autofill.FillEventHistory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;

public class CurriculoAlunoActivity extends AppCompatActivity implements OnItemSelectedListener {

    private TextView dataAniversario;
    private EditText nome, email, endereco;
    private Button botaoSalvar;
    Spinner spinner;
    private DatePickerDialog.OnDateSetListener dataAniversarioListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculo_aluno);
        //mascara do campo telefone
        EditText telefone =(EditText) findViewById(R.id.curriculo_telefoneAluno_editText);
        MaskEditTextChangedListener maskTEL = new MaskEditTextChangedListener("(###) ###-###-###", telefone);
        telefone.addTextChangedListener(maskTEL);

        //Listar genero
        spinner= (Spinner) findViewById(R.id.curriculo_genero_spinner);
        spinner.setOnItemSelectedListener(this);
        List<String>genero = new ArrayList<String>();
        genero.add(" - ");
        genero.add("Feminino");
        genero.add("Masculino");

        ArrayAdapter<String> dadosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,genero);
        dadosAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dadosAdapter);

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
    //Lista spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
