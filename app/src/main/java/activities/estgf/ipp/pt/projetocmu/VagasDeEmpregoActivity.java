package activities.estgf.ipp.pt.projetocmu;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import activities.estgf.ipp.pt.projetocmu.adapter.PageAdapter;
import activities.estgf.ipp.pt.projetocmu.adapter.VagasEmpregoAdapter;
import activities.estgf.ipp.pt.projetocmu.dao.CurriculoDao;
import activities.estgf.ipp.pt.projetocmu.fragments.Fragment1VagasDeEmprego;
import activities.estgf.ipp.pt.projetocmu.fragments.Fragment2VagasDeEmprego;
import activities.estgf.ipp.pt.projetocmu.fragments.Framgent1AlunosCandidatos;
import activities.estgf.ipp.pt.projetocmu.modelo.Curriculo;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class VagasDeEmpregoActivity extends AppCompatActivity {

    private Intent intentLogin;
    private long idDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vagas_de_emprego);

        intentLogin = getIntent();
        idDoAluno = intentLogin.getLongExtra("idDoAluno",0);

        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, Fragment1VagasDeEmprego.class.getName()));
        fragments.add(Fragment.instantiate(this, Fragment2VagasDeEmprego.class.getName()));

        //Pasando valor no fragmento1
        Bundle bundle = new Bundle();
        bundle.putLong("idDoAluno", idDoAluno);
        fragments.get(0).setArguments(bundle);

        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), fragments);
        final ViewPager pager = (ViewPager) findViewById(R.id.viewpager);

        pager.setAdapter(adapter);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                pager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                pager.setCurrentItem(tab.getPosition());
            }
        };
        actionBar.addTab(actionBar.newTab().setText("Sugeridos").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Maps").setTabListener(tabListener));
    }


    @Override
    protected void onResume() {
        super.onResume();

        //Reload Fragmentos
        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, Fragment1VagasDeEmprego.class.getName()));
        fragments.add(Fragment.instantiate(this, Fragment2VagasDeEmprego.class.getName()));

        //Pasando valor no fragmento1
        Bundle bundle = new Bundle();
        bundle.putLong("idDoAluno", idDoAluno);
        fragments.get(0).setArguments(bundle);

        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), fragments);
        final ViewPager pager = (ViewPager) findViewById(R.id.viewpager);

        pager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vagas_emprego, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuVagas_curriculo_item:
                //Toast.makeText(VagasDeEmpregoActivity.this, "Tocou em curriculo", Toast.LENGTH_SHORT).show();
                Intent vaiParaCurriculo = new Intent(this, CurriculoAlunoActivity.class);
                CurriculoDao curriculoDao = new CurriculoDao(VagasDeEmpregoActivity.this);
                Curriculo curriculo  = curriculoDao.existeCurriculo(idDoAluno);
                vaiParaCurriculo.putExtra("curriculo", curriculo);
                vaiParaCurriculo.putExtra("idDoAluno",idDoAluno);
                startActivity(vaiParaCurriculo);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
