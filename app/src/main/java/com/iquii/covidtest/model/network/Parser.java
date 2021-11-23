package com.iquii.covidtest.model.network;

import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parser {

    List<CountryData> parse(Response response) throws JSONException {
        JSONObject newJson = new JSONObject(response.getBody());
        ArrayList<CountryData> countries = new ArrayList<>();
        // Get keys from json
        Iterator<String> panelKeys = newJson.keys();
        while(panelKeys.hasNext()) {
            JSONObject panel = newJson.getJSONObject(panelKeys.next()); // get key from list
            String country  = panel.getString(Constants.COUNTRY);
            int population  = panel.getInt(Constants.POPULATION);
            int active = panel.getInt(Constants.CONFIRMED);
            int deaths = panel.getInt(Constants.DEATHS);
            String updated = panel.getString(Constants.UPDATED);
            countries.add(new CountryData(active,deaths,country,population, updated));
        }
        return countries;
    }

}
