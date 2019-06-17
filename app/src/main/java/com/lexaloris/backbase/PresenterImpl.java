package com.lexaloris.backbase;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class PresenterImpl implements Presenter {

    private static final String TAG = PresenterImpl.class.getSimpleName();
    private static final String FILE_NAME = "cities.json";

    private String cities = null;
    private WeakReference<MainView> mainView;
    private Context context;

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
            cities = loadCities();
        }

        MainView view = mainView.get();
        if (view != null) {
            ArrayList<String> myDataset = new ArrayList<>();
            myDataset.add("Lexa");
            myDataset.add("Loris");
            view.populate(myDataset);
        }
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
