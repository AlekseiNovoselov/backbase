package com.lexaloris.backbase;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.lexaloris.backbase.model.Cities;
import com.lexaloris.backbase.model.City;

interface MainView extends OnMapReadyCallback {

    void populate(Cities model);

    void showCityOnMap(City selectedCity);
}
