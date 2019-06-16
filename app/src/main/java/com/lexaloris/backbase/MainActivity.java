package com.lexaloris.backbase;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            return;
        }
        if (savedInstanceState != null) {
            Fragment fragmentById = getFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragmentById != null) {
                getFragmentManager().beginTransaction().remove(fragmentById).commit();
            }
        }
        ListFragment listFragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listFragment).commit();
    }
}
