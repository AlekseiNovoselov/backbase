package com.lexaloris.backbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewStub;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {

    private CitiesListAdapter mAdapter;

    private MapView mapView;
    private GoogleMap map;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachPresenter();
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            ViewStub viewStub = findViewById(R.id.viewStub);
            viewStub.inflate();
            mapView = findViewById(R.id.mapview);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        }

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CitiesListAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    private void attachPresenter() {
        presenter = (Presenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new PresenterImpl(getApplicationContext());
        }
        presenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void populate(ArrayList<String> model) {
        mAdapter.update(model);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onResume() {
        if (mapView != null) {
            mapView.onResume();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mapView != null) {
            mapView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }
}
