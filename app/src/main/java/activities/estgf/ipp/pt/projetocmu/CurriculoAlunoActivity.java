package activities.estgf.ipp.pt.projetocmu;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
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
import java.util.regex.Pattern;

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
    private Intent intentVagasDeEmprego;
    private boolean nomeEvalido,emailEvalido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculo_aluno);
        ActionBar teste = getActionBar();
        setTitle("Curriculo");

        intentVagasDeEmprego = getIntent();
        final long idDoAluno = intentVagasDeEmprego.getLongExtra("idDoAluno", 0);
        final Curriculo curriculo = (Curriculo) intentVagasDeEmprego.getSerializableExtra("curriculo");

        nome = (EditText) findViewById(R.id.curriculo_nomeAluno_editText);
        dataAniversario = (TextView) findViewById(R.id.curriculo_dataAniversario_textView);
        spinnerGenero = (Spinner) findViewById(R.id.curriculo_genero_spinner);
        telefone = (EditText) findViewById(R.id.curriculo_telefoneAluno_editText);
        email = (EditText) findViewById(R.id.curriculo_emailAluno_editText);
        endereco = (EditText) findViewById(R.id.curriculo_enderecoAluno_editText);
        objetivo = (EditText) findViewById(R.id.curriculo_objetivo_editText);
        curso = (EditText) findViewById(R.id.curriculo_formcao_editText);
        empresa = (EditText) findViewById(R.id.curriculo_empresa_editText);
        cargo = (EditText) findViewById(R.id.curriculo_cargo_editText);
        periodo = (EditText) findViewById(R.id.curriculo_duracao_editText);
        idioma1 = (EditText) findViewById(R.id.curriculo_idioma1_editText);
        idioma2 = (EditText) findViewById(R.id.curriculo_idioma2_editText);

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

        if (curriculo != null){
            nome.setText(curriculo.getNome());
            dataAniversario.setText(curriculo.getDataNasc());
            telefone.setText(curriculo.getTelefone());
            email.setText(curriculo.getEmail());
            endereco.setText(curriculo.getEnderenco());
            objetivo.setText(curriculo.getObejtivo());
            curso.setText(curriculo.getCurso());
            empresa.setText(curriculo.getEmpresa());
            cargo.setText(curriculo.getCargo());
            periodo.setText(curriculo.getPerido());
            idioma1.setText(curriculo.getIdioma1());
            idioma2.setText(curriculo.getIdioma2());
            setSpinerText(spinnerGenero,curriculo.getSexo(),dadosAdapter);
        }


        // Calendario data nascimento
        dataDeAniversario();

        //botao salvar onClick
        nomeEvalido = Pattern.matches("^[A-Za-z\\s]+(\\s[A-Za-z]+)$",nome.getText());
        emailEvalido = Pattern.matches("\"^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\"\n" +
                                            "\t\t+ \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$",email.getText());

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

                    Toast.makeText(CurriculoAlunoActivity.this, "Verifique se os campos estão preenchidos corretamente"+ curriculo.getIdAluno(), Toast.LENGTH_SHORT).show();
                }
                else{
                    CurriculoDao dao = new CurriculoDao(CurriculoAlunoActivity.this);

                    if(curriculo.getId() != 0 && curriculo.getId() != null){
                        dao.atualizaCurriculo(setandoCurriculoAtualizado(curriculo));
                        Toast.makeText(CurriculoAlunoActivity.this,"Atualizado Com Sucesso", Toast.LENGTH_SHORT).show();

                    }else{
                        Curriculo curriculoAux = new Curriculo(idDoAluno,nome.getText().toString(),dataAniversario.getText().toString(),
                                spinnerGenero.getSelectedItem().toString(),telefone.getText().toString(),
                                email.getText().toString(),endereco.getText().toString(),objetivo.getText().toString(),curso.getText().toString(),
                                empresa.getText().toString(),cargo.getText().toString(),periodo.getText().toString(),idioma1.getText().toString(),idioma2.getText().toString());
                        dao.insereCurriculo(curriculoAux);
                        Toast.makeText(CurriculoAlunoActivity.this, "Curriculo Salvo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void  setSpinerText(Spinner spinnerGenero, String genero,ArrayAdapter dados){
        int posicaoArray = 0;

        for(int i=0; (i <= dados.getCount()-1) ; i++){
            if(dados.getItem(i).equals(genero)){
                posicaoArray = i;
                break;
            }else{
                posicaoArray = 0;
            }
        }
        spinnerGenero.setSelection(posicaoArray);
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

    public Curriculo setandoCurriculoAtualizado (Curriculo curriculo){
        curriculo.setNome(nome.getText().toString());
        curriculo.setDataNasc(dataAniversario.getText().toString());
        curriculo.setSexo(spinnerGenero.getSelectedItem().toString());
        curriculo.setTelefone(telefone.getText().toString());
        curriculo.setEmail(email.getText().toString());
        curriculo.setEnderenco(endereco.getText().toString());
        curriculo.setObejtivo(objetivo.getText().toString());
        curriculo.setCurso(curso.getText().toString());
        curriculo.setEmpresa(empresa.getText().toString());
        curriculo.setCargo(cargo.getText().toString());
        curriculo.setPerido(periodo.getText().toString());
        curriculo.setIdioma1(idioma1.getText().toString());
        curriculo.setIdioma2(idioma2.getText().toString());
        return curriculo;
    }

}
