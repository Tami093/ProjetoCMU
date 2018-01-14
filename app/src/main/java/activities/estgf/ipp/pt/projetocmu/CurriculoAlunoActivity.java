package activities.estgf.ipp.pt.projetocmu;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.dao.CurriculoDao;
import activities.estgf.ipp.pt.projetocmu.dao.EmpresaDAO;
import activities.estgf.ipp.pt.projetocmu.dao.HelperDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Curriculo;
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;

public class CurriculoAlunoActivity extends AppCompatActivity implements OnItemSelectedListener {

    private TextView dataAniversario;
    private EditText nome, email, endereco,telefone,objetivo,empresa,cargo,periodo,idioma1,idioma2,curso;
    private Button botaoSalvar;
    private Spinner spinnerGenero;
    private DatePickerDialog.OnDateSetListener dataAniversarioListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculo_aluno);

        nome = (EditText) findViewById(R.id.curriculo_nomeAluno_editText);
        dataAniversario = (TextView) findViewById(R.id.curriculo_dataAniversario_textView);
        spinnerGenero = (Spinner)  findViewById(R.id.curriculo_genero_spinner);
        telefone = (EditText)findViewById(R.id.curriculo_telefoneAluno_editText);
        email = (EditText)findViewById(R.id.curriculo_emailAluno_editText);
        endereco = (EditText)findViewById(R.id.curriculo_enderecoAluno_editText);
        objetivo = (EditText)findViewById(R.id.curriculo_objetivo_editText);
        curso = (EditText) findViewById(R.id.curriculo_formcao_editText);
        empresa = (EditText)findViewById(R.id.curriculo_empresa_editText);
        cargo =(EditText)findViewById(R.id.curriculo_cargo_editText);
        periodo =(EditText) findViewById(R.id.curriculo_duracao_editText);
        idioma1 = (EditText)findViewById(R.id.curriculo_idioma1_editText);
        idioma2= (EditText)findViewById(R.id.curriculo_idioma2_editText);


        //mascara do campo telefone
        MaskEditTextChangedListener maskTEL = new MaskEditTextChangedListener("(###) ###-###-###", telefone);
        telefone.addTextChangedListener(maskTEL);
        //Listar genero
        spinnerGenero= (Spinner) findViewById(R.id.curriculo_genero_spinner);
        spinnerGenero.setOnItemSelectedListener(this);
        final List<String>genero = new ArrayList<String>();
        genero.add(" - ");
        genero.add("Feminino");
        genero.add("Masculino");

        ArrayAdapter<String> dadosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,genero);
        dadosAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerGenero.setAdapter(dadosAdapter);
        // Calendario data nascimento
        dataDeAniversario();
        //botao salvar onClick
        botaoSalvar = (Button)findViewById(R.id.curriculo_botaoSalvar_button);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nome.getText().toString().equals("") || nome.getText().toString().equals(null)
                        ||dataAniversario.getText().toString().equals("") || dataAniversario.getText().toString().equals(null)
                        ||spinnerGenero.getSelectedItem().toString().equals("")|| spinnerGenero.getSelectedItem().toString().equals(null)
                        ||telefone.getText().toString().equals("") || telefone.getText().toString().equals(null)
                        ||email.getText().toString().equals("") || email.getText().toString().equals(null)
                        ||endereco.getText().toString().equals("") || endereco.getText().toString().equals(null)
                        ||objetivo.getText().toString().equals("") || objetivo.getText().toString().equals(null)
                        ||curso.getText().toString().equals("") || curso.getText().toString().equals(null)){

                    Toast.makeText(CurriculoAlunoActivity.this, "Verifique se os campos estão preenchidos corretamente", Toast.LENGTH_SHORT).show();
                }
                else{
                    CurriculoDao dao = new CurriculoDao(CurriculoAlunoActivity.this);
                    Curriculo curriculo = new Curriculo(1,nome.getText().toString(),dataAniversario.getText().toString(),
                                                        spinnerGenero.getSelectedItem().toString(),telefone.getText().toString(),
                                                        email.getText().toString(),endereco.getText().toString(),objetivo.getText().toString(),curso.getText().toString(),
                                                        empresa.getText().toString(),cargo.getText().toString(),periodo.getText().toString(),idioma1.getText().toString(),idioma2.getText().toString());

                    dao.insereCurriculo(curriculo);
                }

            }
        });
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
    //Calendario data de nascimento
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
