package activities.estgf.ipp.pt.projetocmu.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activities.estgf.ipp.pt.projetocmu.R;

public class Fragment2VagasDeEmprego extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(container == null){
            return null;
        }

        return inflater.inflate(R.layout.fragment2_vaga_de_emprego, container, false);
    }
}
