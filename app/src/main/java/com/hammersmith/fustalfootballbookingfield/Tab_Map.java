package com.hammersmith.fustalfootballbookingfield;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by USER on 10/1/2015.
 */
public class Tab_Map extends RootFragmgmet {
    static final LatLng map = new LatLng(21,57);
    private GoogleMap googleMap;

//    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
//    static final LatLng KIEL = new LatLng(53.551, 9.993);
//    private GoogleMap map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_map,container, false);

//        map = ((SupportMapFragment)getFragmentManager().findFragmentById(R.id.Map)).getMap();
//        Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG).title("Hamburg"));
//        Marker kiel = map.addMarker(new MarkerOptions().position(KIEL).title("Kiel")
//        .snippet("Kiel is cool").icon(BitmapDescriptorFactory.fromResource(R.drawable.ball)));
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG,15));
//        map.animateCamera(CameraUpdateFactory.zoomTo(10),2000,null);
        showMap();

        return view;
    }

    private void showMap() {
        try{
            if(googleMap == null){
                googleMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.Map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(map).title("Phnom Penh"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
