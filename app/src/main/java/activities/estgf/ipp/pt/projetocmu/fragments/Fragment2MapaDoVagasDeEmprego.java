package activities.estgf.ipp.pt.projetocmu.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import activities.estgf.ipp.pt.projetocmu.R;

public class Fragment2MapaDoVagasDeEmprego extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        getMapAsync(this);

        /*
           super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment2_mapa_do_vagas_de_emprego);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment2MapaVagas_mapa_fragment);
        mapFragment.getMapAsync(this);
        */

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng posicaoEscolhida = pegaCoodernadaDoEndereco("Rua Vergueiro 3185, Vila Mariana, Sao Paulo");

        if(posicaoEscolhida != null){
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoEscolhida, 17);
            googleMap.moveCamera(update);
        }


        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }



    private LatLng pegaCoodernadaDoEndereco (String endereco){
        try {
            Geocoder geocoder = new Geocoder(getContext()); //Geo coder pega String e transforma em coordenadas para a LatLng
            List<Address> resultados =
                    geocoder.getFromLocationName(endereco, 1);

            if (!resultados.isEmpty()){
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
