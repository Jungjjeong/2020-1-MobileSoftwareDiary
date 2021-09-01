package com.example.jiyoungdiary;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Double lati;
    Double longi;
    String place_name;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
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
        Intent intent = getIntent();
        lati = intent.getDoubleExtra("latitude",0.0);
        longi = intent.getDoubleExtra("longitude",0.0);
        place_name = intent.getStringExtra("name");

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(lati, longi);
        // 마커에 대한 옵션 설정
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location);
        markerOptions.title(place_name);
        markerOptions.snippet("여행 장소");
        mMap.addMarker(markerOptions);
        // 줌 기능 활성화
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        // 현재 위치로 이동
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        // 줌 레벨 설정
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}