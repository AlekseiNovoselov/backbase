package com.lexaloris.backbase.mainlist.presentation.view;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.lexaloris.backbase.mainlist.entities.Cities;
import com.lexaloris.backbase.mainlist.entities.City;

public interface MainView extends OnMapReadyCallback {

    void populate(Cities model);

    void showCityOnMap(City selectedCity);
}
