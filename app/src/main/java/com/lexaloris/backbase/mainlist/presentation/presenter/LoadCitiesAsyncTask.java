package com.lexaloris.backbase.mainlist.presentation.presenter;

import android.os.AsyncTask;

import com.lexaloris.backbase.mainlist.domain.CitiesRepository;
import com.lexaloris.backbase.mainlist.entities.CitiesData;

import java.lang.ref.WeakReference;

public class LoadCitiesAsyncTask extends AsyncTask<Void, Void, Void> {
    private final WeakReference<MainPresenterImpl> mainPresenterWeakReference;
    private final WeakReference<CitiesRepository> repositoryWeakReference;

    public LoadCitiesAsyncTask(
            MainPresenterImpl mainPresenterWeakReference,
            CitiesRepository repository
    ) {
        this.mainPresenterWeakReference = new WeakReference<>(mainPresenterWeakReference);
        this.repositoryWeakReference = new WeakReference<>(repository);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MainPresenterImpl presenter = mainPresenterWeakReference.get();
        if (presenter != null) {
            presenter.showProgress();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            CitiesData citiesData = repositoryWeakReference.get().loadCities();
            MainPresenterImpl presenter = mainPresenterWeakReference.get();
            if (presenter != null) {
                presenter.setCitiesData(citiesData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainPresenterImpl presenter = mainPresenterWeakReference.get();
        if (presenter != null) {
            presenter.hideProgress();
            presenter.populateCities();
        }
    }
}
