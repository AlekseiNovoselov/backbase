package com.lexaloris.backbase;

import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;

interface MainView extends OnMapReadyCallback {

    void populate(ArrayList<String> model);
}
