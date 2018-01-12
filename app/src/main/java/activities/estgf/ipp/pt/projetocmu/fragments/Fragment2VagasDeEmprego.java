package activities.estgf.ipp.pt.projetocmu.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.R;
import activities.estgf.ipp.pt.projetocmu.dao.VagaDAO;
import activities.estgf.ipp.pt.projetocmu.modelo.Vaga;

public class Fragment2VagasDeEmprego extends Fragment implements OnMapReadyCallback {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(container == null){ return null; }

        return inflater.inflate(R.layout.fragment2_vaga_de_emprego, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //GoogleMap mGooglemap =
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment2_mapa_fragment)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng posicaoEscolhida = pegaCoodernadaDoEndereco("Rua Julio Dinis, Felgueiras");

        if(posicaoEscolhida != null){
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoEscolhida, 17);
            googleMap.moveCamera(update);
        }

        VagaDAO vagaDao = new VagaDAO(getContext());
        for(Vaga vaga : vagaDao.buscaVagas()){
            LatLng coordenada = pegaCoodernadaDoEndereco(vaga.getLocalTrabalho());
            if(coordenada != null){
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(vaga.getNomeEmpresa());
                marcador.snippet(String.valueOf(vaga.getSalario()) + " Euros");
                googleMap.addMarker(marcador);
            }
        }

    }

    //Pega Localizacao Latitude e Longitude de acordo com o endereco
    private LatLng pegaCoodernadaDoEndereco (String endereco){
        try {
            Geocoder geocoder = new Geocoder(getContext()); //Geo coder pega String e transforma em coordenadas para a LatLng
            List<Address> resultados =
                geocoder.getFromLocationName(endereco, 1);

            if (!resultados.isEmpty()){
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
