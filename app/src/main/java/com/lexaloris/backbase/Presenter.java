package com.lexaloris.backbase;

interface Presenter {

    void attachView(MainActivity mainActivity);

    void detachView();

    void onStart();
}
