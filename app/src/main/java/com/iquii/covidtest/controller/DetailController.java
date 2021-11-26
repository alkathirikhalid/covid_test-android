package com.iquii.covidtest.controller;

import android.os.AsyncTask;
import android.os.Bundle;

import com.iquii.covidtest.model.DataAccessLayer;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.entity.DataPoint;
import com.iquii.covidtest.utils.Constants;
import com.iquii.covidtest.view.details.DetailView;

import java.util.ArrayList;

public class DetailController {

    private DetailView view;
    private DataAccessLayer model;
    private NetworkAsyncTask asyncTask;
    private ArrayList<CountryData> cachedList = new ArrayList();

    public void bind(DetailView view, DataAccessLayer model){
        this.view = view;
        this.model = model;
        this.asyncTask = new NetworkAsyncTask();

    }

    public void unbind(){
        asyncTask.cancel(true);
        this.view = null;
        this.model = null;
    }

    public void init(){
        if(view != null && model != null && model.getHistoryObserver()!= null && asyncTask != null){
            Bundle arguments = view.getArguments();
            CountryData data = arguments.getParcelable(Constants.ARG_DETAIL);
            model.getHistoryObserver().renderCountry(data);
            asyncTask.execute(data.getCountry());
        }
    }

    public class NetworkAsyncTask extends AsyncTask<String, Void, ArrayList<DataPoint>> {
        @Override
        protected ArrayList<DataPoint> doInBackground(String... strings) {
            return model.performHistory(strings[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<DataPoint> data) {
            if (model.getHistoryObserver() != null) {
                model.getHistoryObserver().renderChart(data);
            }
        }
    }
}



