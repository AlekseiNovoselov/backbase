package com.lexaloris.backbase;

import com.lexaloris.backbase.model.City;

interface Presenter {

    void attachView(MainView mainActivity);

    void onStart();

    void onStop();

    void onItemClick(City city);

    void onMapReady();

    void onTextChanges(String inputtedText);
}
