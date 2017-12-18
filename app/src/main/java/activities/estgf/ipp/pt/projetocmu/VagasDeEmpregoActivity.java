package activities.estgf.ipp.pt.projetocmu;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;
import java.util.Vector;
import activities.estgf.ipp.pt.projetocmu.adapter.PageAdapter;
import activities.estgf.ipp.pt.projetocmu.fragments.Fragment1VagasDeEmprego;
import activities.estgf.ipp.pt.projetocmu.fragments.Fragment2VagasDeEmprego;

public class VagasDeEmpregoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vagas_de_emprego);


        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, Fragment1VagasDeEmprego.class.getName()));
        fragments.add(Fragment.instantiate(this, Fragment2VagasDeEmprego.class.getName()));

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

            }
        };

        actionBar.addTab(actionBar.newTab().setText("Sugeridos").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Mensagens").setTabListener(tabListener));


        /*
        pager.addOnAdapterChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            public void onPageSelected (int position){
                actionBar.setSelectedNavigationItem(position);
            }
        });
        */

    }
}
