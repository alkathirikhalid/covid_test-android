package com.iquii.covidtest.model;


import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.network.ConnectionManager;
import com.iquii.covidtest.model.network.Response;
import com.iquii.covidtest.model.network.parser.ListParser;
import com.iquii.covidtest.model.observer.CountryObserver;
import com.iquii.covidtest.model.observer.DomainObserver;

import org.json.JSONException;

import java.util.ArrayList;

public class DataAccessLayer {

    private static final String URL_CASES = "https://covid-api.mmediagroup.fr/v1/cases";
    private static final String URL_DETAIL = "https://covid-api.mmediagroup.fr/v1/history";

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


    public CountryObserver getCountryObserver() {
        for (DomainObserver o : observers) {
            if(o instanceof CountryObserver){
                return ((CountryObserver)o);
            }
        }
        return null;
    }
}
