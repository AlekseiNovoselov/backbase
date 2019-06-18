package com.lexaloris.backbase;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lexaloris.backbase.model.Cities;
import com.lexaloris.backbase.model.CitiesData;
import com.lexaloris.backbase.model.City;
import java.lang.ref.WeakReference;

public class PresenterImpl implements Presenter {

    private WeakReference<MainView> mainView;
    private CitiesRepository repository;
    private SearchUtils searchUtils = new SearchUtils();
    private CitiesData citiesData = null;
    private City selectedCity = null;
    private String searchText = "";

    public PresenterImpl(@NonNull Context context) {
        repository = new CitiesRepository(context);
    }

    @Override
    public void attachView(MainView mainView) {
        this.mainView = new WeakReference<>(mainView);
    }

    @Override
    public void onStart() {
        if (citiesData == null) {
            citiesData = repository.loadCities();
        }
        populateCities();
    }

    private void populateCities() {
        MainView view = mainView.get();
        if (view != null) {
            Cities filteredCities = searchUtils.startWithPrefix(citiesData, searchText);
            view.populate(filteredCities);
        }
    }

    @Override
    public void onItemClick(City city) {
        selectedCity = city;
        showSelectedCity();
    }

    private void showSelectedCity() {
        MainView view = mainView.get();
        if (view != null) {
            view.showCityOnMap(selectedCity);
        }
    }

    @Override
    public void onMapReady() {
        showSelectedCity();
    }

    @Override
    public void onTextChanges(String inputtedText) {
        searchText = inputtedText;
        populateCities();
    }

    @Override
    public void onStop() {

    }
}
