package com.iquii.covidtest.model.network;

import android.util.Log;

import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parser {

    public  static ArrayList<CountryData> parse(Response response) throws JSONException {

        JSONObject newJson = new JSONObject(response.getBody());
        ArrayList<CountryData> countries = new ArrayList<>();
        // Get keys from json

        Iterator<String> panelKeys = newJson.keys();

        while(panelKeys.hasNext()) {
            //Log.d("RESPONSE",panelKeys.next());

            JSONObject panel = newJson.getJSONObject(panelKeys.next());

            JSONObject allObject = panel.optJSONObject(Constants.ALL);
            // get key from list
            String country  = allObject.optString(Constants.COUNTRY,"");
            int population  = allObject.optInt(Constants.POPULATION,0);
            int active = allObject.getInt(Constants.CONFIRMED);
            int deaths = allObject.getInt(Constants.DEATHS);
            //FILTER ITEM REMOVING CORRUPTED DATA
            if(!country.isEmpty())
                countries.add(new CountryData(active,deaths,country,population));
        }
        return countries;
    }

}
