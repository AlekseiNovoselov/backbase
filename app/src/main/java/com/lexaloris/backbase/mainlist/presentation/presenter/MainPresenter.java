package com.lexaloris.backbase.mainlist.presentation.presenter;

import com.lexaloris.backbase.mainlist.entities.City;
import com.lexaloris.backbase.mainlist.presentation.view.MainView;

public interface MainPresenter {

    void attachView(MainView mainActivity);

    void onStart();

    void onStop();

    void onItemClick(City city);

    void onMapReady();

    void onTextChanges(String inputtedText);
}
