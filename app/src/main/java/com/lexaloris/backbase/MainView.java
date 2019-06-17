package com.lexaloris.backbase;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.lexaloris.backbase.model.Cities;

interface MainView extends OnMapReadyCallback {

    void populate(Cities model);
}
