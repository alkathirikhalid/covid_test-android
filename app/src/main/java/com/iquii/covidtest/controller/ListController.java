package com.iquii.covidtest.controller;

import android.os.AsyncTask;

import com.iquii.covidtest.model.DataAccessLayer;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.view.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ListController {

    private ListView view;
    private DataAccessLayer model;
    private NetworkAsyncTask asyncTask;
    private ArrayList<CountryData> cachedList = new ArrayList();


    public void bind(ListView view, DataAccessLayer model){
      this.view = view;
      this.model = model;
      this.asyncTask = new NetworkAsyncTask();
      asyncTask.execute();
    }

    public void unbind(){
        asyncTask.cancel(true);
        this.view = null;
        this.model = null;
    }

    public void search(){
        CharSequence constraint = view.getSearchInput();
        ArrayList<CountryData> filteredList = new ArrayList<>();
        String filterPattern = constraint.toString().toLowerCase().trim();
        for(CountryData item : cachedList){
            if(item.getCountry().toLowerCase().startsWith(filterPattern)){
                filteredList.add(item);
            }
        }
        model.getCountryObserver().renderList(filteredList);
    }

    public void sortByActive(){
        cachedList.sort(Comparator.comparingInt(CountryData::getActive).reversed());
        model.getCountryObserver().renderList(cachedList);
    }

    public void sortByDeaths(){
        cachedList.sort(Comparator.comparingInt(CountryData::getDeaths).reversed());
        model.getCountryObserver().renderList(cachedList);
    }

    public void sortByActiveRatio(){
        cachedList.sort(Comparator.comparingDouble(CountryData::getActiveRatio).reversed());
        model.getCountryObserver().renderList(cachedList);
    }

    public void sortByDeathsRatio(){
        cachedList.sort(Comparator.comparingDouble(CountryData::getDeathsRatio).reversed());
        model.getCountryObserver().renderList(cachedList);
    }

    public class NetworkAsyncTask extends AsyncTask<String, Void, ArrayList<CountryData>> {
        @Override
        protected void onPreExecute() {
            model.getCountryObserver().loader(true);
        }

        @Override
        protected ArrayList<CountryData> doInBackground(String... strings) {
            cachedList = model.performCases();
            return cachedList;
        }

        @Override
        protected void onPostExecute(ArrayList<CountryData> data) {
            model.getCountryObserver().loader(false);
            model.getCountryObserver().renderList(data);
        }
    }
}
