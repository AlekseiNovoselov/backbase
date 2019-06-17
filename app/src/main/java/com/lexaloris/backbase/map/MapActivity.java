package com.lexaloris.backbase.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lexaloris.backbase.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String LAN_PARAM = "LAN_PARAM";
    private static final String LNG_PARAM = "LNG_PARAM";
    private static final String CITY_NAME_PARAM = "CITY_NAME_PARAM";

    private double lan;
    private double lng;
    private String cityName;

    public static void show(Context context, double lan, double lng, String cityName) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra(LAN_PARAM, lan);
        intent.putExtra(LNG_PARAM, lng);
        intent.putExtra(CITY_NAME_PARAM, cityName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        parseParameters();
        setToolbarClickListener();
        startMapInitialization();
    }

    private void parseParameters() {
        Bundle args = getIntent().getExtras();
        if (args != null) {
            lan = args.getDouble(LAN_PARAM);
            lng = args.getDouble(LNG_PARAM);
            cityName = args.getString(CITY_NAME_PARAM);
        }
    }

    private void setToolbarClickListener() {
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void startMapInitialization() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        SupportMapFragment mapFragment = (SupportMapFragment) fragment;
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng position = new LatLng(lan, lng);
        googleMap.clear();
        googleMap.addMarker(new MarkerOptions().position(position).title(cityName));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
    }
}
