package com.lexaloris.backbase;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class PresenterImpl implements Presenter {

    private String cities = null;
    private WeakReference<MainActivity> mainActivity;

    private static final String TAG = PresenterImpl.class.getSimpleName();
    private static final String FILE_NAME = "cities.json";

    @Override
    public void attachView(MainActivity mainActivity) {
        this.mainActivity = new WeakReference<>(mainActivity);
    }

    @Override
    public void detachView() {

    }

    @Override
    public void onStart() {
        if (cities == null) {
            cities = loadCities();
        }
    }

    private String loadCities() {
        if (mainActivity.get() != null) {
            try {
                AssetManager manager = mainActivity.get().getAssets();
                InputStream file = manager.open(FILE_NAME);
                byte[] formArray = new byte[file.available()];
                file.read(formArray);
                file.close();
                return new String(formArray);
            } catch (IOException ex) {
                Log.e(TAG, ex.getLocalizedMessage(), ex);
            }
        }
        return null;
    }
}
