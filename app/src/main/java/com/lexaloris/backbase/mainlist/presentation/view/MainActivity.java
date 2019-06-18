package com.lexaloris.backbase.mainlist.presentation.view;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewStub;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lexaloris.backbase.R;
import com.lexaloris.backbase.about.AboutActivity;
import com.lexaloris.backbase.mainlist.presentation.presenter.MainPresenter;
import com.lexaloris.backbase.mainlist.presentation.presenter.MainPresenterImpl;
import com.lexaloris.backbase.map.MapActivity;
import com.lexaloris.backbase.mainlist.entities.Cities;
import com.lexaloris.backbase.mainlist.entities.City;
import com.lexaloris.backbase.mainlist.entities.Coordination;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter mainPresenter;
    private CitiesListAdapter mAdapter;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachPresenter();
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            ViewStub viewStub = findViewById(R.id.view_stub);
            viewStub.inflate();
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.map_fragment);
            SupportMapFragment mapFragment = (SupportMapFragment) fragment;
            mapFragment.getMapAsync(this);
        }

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );
        recyclerView.addItemDecoration(dividerItemDecoration);
        OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void onItemClick(City city) {
                mainPresenter.onItemClick(city);
            }

            @Override
            public void onButtonClick() {
                startAboutActivity();
            }
        };
        mAdapter = new CitiesListAdapter(listener);
        recyclerView.setAdapter(mAdapter);

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String inputtedText) {
                mainPresenter.onTextChanges(inputtedText);
                return true;
            }
        });
    }

    private void attachPresenter() {
        mainPresenter = (MainPresenter) getLastCustomNonConfigurationInstance();
        if (mainPresenter == null) {
            mainPresenter = new MainPresenterImpl(getApplicationContext());
        }
        mainPresenter.attachView(this);
    }

    private void startAboutActivity() {
        AboutActivity.show(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.onStart();
    }

    @Override
    public void populate(Cities model) {
        mAdapter.update(model);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        mainPresenter.onMapReady();
    }

    @Override
    public void showCityOnMap(City selectedCity) {
        if (selectedCity == null) {
            return;
        }
        boolean isLandscapeMode = getResources().getBoolean(R.bool.twoPaneMode);
        Coordination coordination = selectedCity.getCoordination();
        double lat = coordination.getLat();
        double lon = coordination.getLon();
        String cityName = selectedCity.getName();
        if (isLandscapeMode) {
            selectCityOnMap(lat, lon, cityName);
        } else {
            MapActivity.show(this, lat, lon, cityName);
        }
    }

    private void selectCityOnMap(double lat, double lan, String cityName) {
        LatLng position = new LatLng(lat, lan);
        map.clear();
        map.addMarker(new MarkerOptions().position(position).title(cityName));
        map.moveCamera(CameraUpdateFactory.newLatLng(position));
    }

    @Override
    public void onStop() {
        super.onStop();
        mainPresenter.onStop();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mainPresenter;
    }
}
