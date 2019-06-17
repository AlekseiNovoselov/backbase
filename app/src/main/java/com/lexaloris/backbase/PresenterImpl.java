package com.lexaloris.backbase;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lexaloris.backbase.model.Cities;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class PresenterImpl implements Presenter {

    private static final String TAG = PresenterImpl.class.getSimpleName();
    private static final String FILE_NAME = "cities.json";

    private Cities cities = null;
    private WeakReference<MainView> mainView;
    private Context context;

    private Gson gson = new Gson();

    public PresenterImpl(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void attachView(MainView mainView) {
        this.mainView = new WeakReference<>(mainView);
    }

    @Override
    public void onStart() {
        if (cities == null) {
            String result = loadCities();
            cities = parse(result);
        }

        MainView view = mainView.get();
        if (view != null) {
            view.populate(cities);
        }
    }

    private Cities parse(String cities) {
        try {
            return gson.fromJson(cities, Cities.class);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return new Cities();
    }

    private String loadCities() {
        try {
            AssetManager manager = context.getAssets();
            InputStream file = manager.open(FILE_NAME);
            byte[] formArray = new byte[file.available()];
            file.read(formArray);
            file.close();
            return new String(formArray);
        } catch (IOException ex) {
            Log.e(TAG, ex.getLocalizedMessage(), ex);
        }
        return null;
    }

    @Override
    public void onStop() {

    }
}
