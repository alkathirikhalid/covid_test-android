package com.iquii.covidtest.controller;

import android.net.Uri;

import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.network.ConnectionManager;
import com.iquii.covidtest.model.network.NetworkAsyncTask;
import com.iquii.covidtest.utils.Constants;
import com.iquii.covidtest.view.DetailView;

import java.net.URL;
import java.util.ArrayList;

public class DetailController implements GetData<ArrayList<CountryData>> {

    private DetailView view;

    public void bind(DetailView view){
        this.view = view;
    }


    public void getCountryDates(String country) {
        Uri builtUri = Uri.parse(Constants.URL_DETAIL)
                .buildUpon()
                .appendQueryParameter(Constants.PARAM_COUNTRY, country)
                .appendQueryParameter(Constants.PARAM_STATUS, "deaths")
                .build();
        String url = builtUri.toString();
        new NetworkAsyncTask(this).execute(url);

    }

    @Override
    public void getData(ArrayList<CountryData> data) {
        view.showCharts(data.get(0));
    }
}



