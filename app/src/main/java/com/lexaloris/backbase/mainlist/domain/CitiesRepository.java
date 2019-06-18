package com.lexaloris.backbase.mainlist.domain;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lexaloris.backbase.mainlist.presentation.presenter.MainPresenterImpl;
import com.lexaloris.backbase.mainlist.entities.Cities;
import com.lexaloris.backbase.mainlist.entities.CitiesData;
import com.lexaloris.backbase.mainlist.entities.City;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Comparator;

public class CitiesRepository {
    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    private static final String FILE_NAME = "cities.json";

    private final WeakReference<Context> context;
    private Gson gson = new Gson();

    public CitiesRepository(Context context) {
        this.context = new WeakReference<>(context);
    }

    public CitiesData loadCities() {
        if (context.get() != null) {
            try {
                AssetManager manager = context.get().getAssets();
                InputStream file = manager.open(FILE_NAME);
                byte[] formArray = new byte[file.available()];
                file.read(formArray);
                file.close();
                Cities cities = parse(new String(formArray));
                Comparator<City> comparator = createComparator();
                Collections.sort(cities, comparator);
                return new CitiesData(cities);
            } catch (IOException ex) {
                Log.e(TAG, ex.getLocalizedMessage(), ex);
            }
        }
        return null;
    }

    private Cities parse(String cities) {
        try {
            return gson.fromJson(cities, Cities.class);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return new Cities();
    }

    private Comparator<City> createComparator() {
        return (firstCity, secondCity) -> {
            int nameResult = firstCity.getName().compareToIgnoreCase(secondCity.getName());
            if (nameResult != 0) {
                return nameResult;
            }
            return firstCity.getCountryName().compareToIgnoreCase(secondCity.getCountryName());
        };
    }
}
