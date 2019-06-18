package com.lexaloris.backbase.mainlist.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lexaloris.backbase.mainlist.domain.CitiesRepository;
import com.lexaloris.backbase.mainlist.presentation.view.MainView;
import com.lexaloris.backbase.mainlist.utils.SearchUtils;
import com.lexaloris.backbase.mainlist.entities.Cities;
import com.lexaloris.backbase.mainlist.entities.CitiesData;
import com.lexaloris.backbase.mainlist.entities.City;

import java.lang.ref.WeakReference;

public class MainPresenterImpl implements MainPresenter {
    private WeakReference<MainView> mainView;
    private CitiesRepository repository;
    private SearchUtils searchUtils = new SearchUtils();
    private CitiesData citiesData = null;
    private City selectedCity = null;
    private String searchText = "";
    private LoadCitiesAsyncTask loadCitiesAsyncTask;

    public MainPresenterImpl(@NonNull Context context) {
        repository = new CitiesRepository(context);
    }

    @Override
    public void attachView(MainView mainView) {
        this.mainView = new WeakReference<>(mainView);
    }

    @Override
    public void onStart() {
        if (citiesData == null) {
            loadCitiesAsyncTask = new LoadCitiesAsyncTask(this, repository);
            loadCitiesAsyncTask.execute();
        } else {
            populateCities();
        }
    }

    void populateCities() {
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
        if (!searchText.equals(inputtedText)) {
            searchText = inputtedText;
            populateCities();
        }
    }

    @Override
    public void onStop() {
        if (loadCitiesAsyncTask != null) {
            loadCitiesAsyncTask.cancel(true);
        }
    }

    void showProgress() {
        MainView view = mainView.get();
        if (view != null) {
            view.showProgress();
        }
    }

    void setCitiesData(CitiesData citiesData) {
        this.citiesData = citiesData;
    }

    void hideProgress() {
        MainView view = mainView.get();
        if (view != null) {
            view.hideProgress();
        }
    }
}
