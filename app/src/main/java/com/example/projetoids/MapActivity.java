package com.example.projetoids;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);

        Drawable gotaDrawable = getDrawable(R.drawable.gota_sangue);
        Bitmap gotaIconBitmap=((BitmapDrawable) gotaDrawable).getBitmap();
        Bitmap gotaBitmapIcon=Bitmap.createScaledBitmap(gotaIconBitmap,80,80,false);


        LatLng hemoam = new LatLng(-3.086077,-60.027019);
        mMap.addMarker(new MarkerOptions().position(hemoam)
                .title("Hemoam")
                .icon(BitmapDescriptorFactory.fromBitmap(gotaBitmapIcon)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hemoam,18),4000,null);

        LatLng maternidade = new LatLng(-3.0769168,-59.956883);

        mMap.addMarker(new MarkerOptions().position(maternidade)
                .title("Maternidade Ana Braga")
                .icon(BitmapDescriptorFactory.fromBitmap(gotaBitmapIcon)));
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(maternidade,18),4000,null);
    }
}
