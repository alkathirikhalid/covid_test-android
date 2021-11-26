package com.iquii.covidtest.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.iquii.covidtest.R;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //controller.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //controller.unbind();
    }

    /*@Override
    public void navigateToList(ArrayList<CountryData> countryData) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArray(Constants.ARG_LIST, countryData.toArray(new CountryData[0]));
        Navigation.findNavController(this, R.id.nav_host_fragment).setGraph(R.navigation.main_navigation, bundle);
    }*/
}