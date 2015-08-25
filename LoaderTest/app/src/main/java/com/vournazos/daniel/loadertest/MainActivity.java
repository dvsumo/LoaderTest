package com.vournazos.daniel.loadertest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by dvournazos on 8/24/15.
 */
public class MainActivity extends ActionBarActivity {

    private LoaderFragment loaderFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragMan = getSupportFragmentManager();
        loaderFrag = (LoaderFragment) fragMan.findFragmentByTag("frag");

        FragmentTransaction transaction = fragMan.beginTransaction();
        if(loaderFrag == null)
        {
            loaderFrag = new LoaderFragment();
            transaction.add(R.id.fragment_container, loaderFrag, "frag");
        }
        transaction.show(loaderFrag).commit();
    }
}
