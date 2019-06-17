package com.lexaloris.backbase;

interface Presenter {

    void attachView(MainView mainActivity);

    void onStart();

    void onStop();
}
