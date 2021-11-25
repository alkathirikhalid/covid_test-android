package com.iquii.covidtest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.iquii.covidtest.R;
import com.iquii.covidtest.controller.MainController;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.network.NetworkAsyncTask;
import com.iquii.covidtest.utils.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView{

    private final static String TAG = "LIST";
    private MainController controller = new MainController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        controller.unbind();
    }

    @Override
    public void navigateToList(ArrayList<CountryData> countryData) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArray(Constants.ARG_LIST, countryData.toArray(new CountryData[0]));
        Navigation.findNavController(this, R.id.nav_host_fragment).setGraph(R.navigation.main_navigation, bundle);
    }
}