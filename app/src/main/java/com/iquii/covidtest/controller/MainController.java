package com.iquii.covidtest.controller;

import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.network.NetworkAsyncTask;
import com.iquii.covidtest.model.network.parser.ListParser;
import com.iquii.covidtest.utils.Constants;
import com.iquii.covidtest.view.MainView;

import java.util.ArrayList;

public class MainController implements GetData<ArrayList<CountryData>> {

      MainView view;
      NetworkAsyncTask networkAsyncTask = new NetworkAsyncTask(this, new ListParser());

      public void bind(MainView view){
          this.view = view;
            requestCountriesData();

      }

      public void requestCountriesData(){
          networkAsyncTask.execute(Constants.URL_COUNTRIES);
      }


    @Override
    public void getData(ArrayList<CountryData> data) {
        view.navigateToList(data);
    }

    public void unbind(){
          networkAsyncTask.cancel(true);
    }


}
