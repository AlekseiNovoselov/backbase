package com.lexaloris.backbase;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachPresenter();
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

    private void attachPresenter() {
        presenter = (Presenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new PresenterImpl();
        }
        presenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }
}
