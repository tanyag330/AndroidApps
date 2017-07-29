package com.example.tanya.googlemaps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Delhi = new LatLng(28.10, 77.10);
        mMap.addMarker(new MarkerOptions().position(Delhi).title("Marker in Delhi").snippet("welcome to Delhi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Delhi));

        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setCompassEnabled(!mMap.getUiSettings().isCompassEnabled());

        mMap.addPolygon(new PolygonOptions().add(new LatLng(27,77)).add(new LatLng(26,76))
                .add(new LatLng(26,75)).add(new LatLng(27,75)).fillColor(Color.GREEN));

        CameraUpdate cu = CameraUpdateFactory.newLatLng(new LatLng(22,77));
        CameraUpdate cu1 = CameraUpdateFactory.zoomTo(7);

        CameraUpdate cu2 = CameraUpdateFactory.newCameraPosition(
                new CameraPosition(new LatLng(22,77),10,2,5)
        );
        mMap.animateCamera(cu);
        mMap.animateCamera(cu1);
        mMap.animateCamera(cu2,5000, new GoogleMap.CancelableCallback(

        ) {
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancel() {

            }
        });


    }
}
