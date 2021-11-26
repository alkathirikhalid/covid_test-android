package com.iquii.covidtest.model;


import android.net.Uri;
import android.util.Log;

import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.entity.CountryDeaths;
import com.iquii.covidtest.model.entity.DataPoint;
import com.iquii.covidtest.model.network.ConnectionManager;
import com.iquii.covidtest.model.network.Response;
import com.iquii.covidtest.model.network.parser.HistoryParser;
import com.iquii.covidtest.model.network.parser.ListParser;
import com.iquii.covidtest.model.observer.CountryObserver;
import com.iquii.covidtest.model.observer.DomainObserver;
import com.iquii.covidtest.model.observer.HistoryObserver;
import com.iquii.covidtest.utils.Constants;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DataAccessLayer {

    private static DataAccessLayer INSTANCE;
    private String info = "Data module class";

    private DataAccessLayer() {
    }

    public static DataAccessLayer getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataAccessLayer();
        }

        return INSTANCE;
    }

    private static final String URL_CASES = "https://covid-api.mmediagroup.fr/v1/cases";
    private static final String URL_HISTORY = "https://covid-api.mmediagroup.fr/v1/history";

    private ArrayList<DomainObserver> observers = new ArrayList<>() ;

    public void register(DomainObserver observer){
        observers.add(observer);
    }

    public void unregister(DomainObserver observer){
        observers.remove(observer);
    }

    public ArrayList<CountryData> performCases() {
        ListParser parser = new ListParser();
        Response response = ConnectionManager.connect(URL_CASES);
        if (response.isSuccesful()) {
            try {
                return parser.parse(response);
            } catch (JSONException e) {
                e.printStackTrace();
                return new ArrayList();
            }
        }
        return new ArrayList();
    }

    public ArrayList<DataPoint> performHistory(String country) {
        Uri builtUri = Uri.parse(URL_HISTORY)
                .buildUpon()
                .appendQueryParameter(Constants.PARAM_COUNTRY, country)
                .appendQueryParameter(Constants.PARAM_STATUS, "deaths")
                .build();
        HistoryParser parser = new HistoryParser();
        Response response = ConnectionManager.connect(builtUri.toString());
        if (response.isSuccesful()) {
            try {
                List<CountryData> data = parser.parse(response);
                List<CountryDeaths> deaths = data.get(0).getCountryDeathsList();
                if(!deaths.isEmpty() && deaths.size() >= 11){
                    deaths = deaths.subList(0,11);
                    Collections.reverse(deaths);
                    ArrayList<DataPoint> deathsValues = new ArrayList<>();
                    for(int i = 0; i < deaths.size()-1; i++){
                        int deathNumberDay = deaths.get(i+1).getQuantity() - deaths.get(i).getQuantity();
                        Log.d("DEATH", Integer.toString(deathNumberDay));
                        deathsValues.add(new DataPoint(i,deathNumberDay));
                    }
                    return deathsValues;
                }
                return new ArrayList();
            } catch (JSONException e) {
                e.printStackTrace();
                return new ArrayList();
            }
        }
        return new ArrayList();
    }


    public CountryObserver getCountryObserver() {
        for (DomainObserver o : observers) {
            if(o instanceof CountryObserver){
                return ((CountryObserver)o);
            }
        }
        return null;
    }

    public HistoryObserver getHistoryObserver() {
        for (DomainObserver o : observers) {
            if(o instanceof HistoryObserver){
                return ((HistoryObserver)o);
            }
        }
        return null;
    }
}
