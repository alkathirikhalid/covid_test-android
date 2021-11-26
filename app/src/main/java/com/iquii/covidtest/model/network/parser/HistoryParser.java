package com.iquii.covidtest.model.network.parser;

import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.entity.CountryDeaths;
import com.iquii.covidtest.model.network.Response;
import com.iquii.covidtest.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class HistoryParser implements Parser {

    public   ArrayList<CountryData> parse(Response response) throws JSONException {

        JSONObject newJson = new JSONObject(response.getBody());
        ArrayList<CountryData> countries = new ArrayList<>();
        ArrayList<CountryDeaths> countryDeaths = new ArrayList<>();

            JSONObject allObject = newJson.optJSONObject(Constants.ALL);
            // get key from list
            String country  = allObject.optString(Constants.COUNTRY,"");
            int active = allObject.optInt(Constants.CONFIRMED,0);
            int deaths = allObject.optInt(Constants.DEATHS,0);
            String dates = allObject.optString(Constants.DATES,"");
            if(!dates.isEmpty()){
                JSONObject dateObject = allObject.getJSONObject(Constants.DATES);
                Iterator<String> dateKeys = dateObject.keys();
                while (dateKeys.hasNext()){
                    String date = dateKeys.next();
                    int value = dateObject.optInt(date,0);
                    countryDeaths.add(new CountryDeaths(date,value));
                }
            }
            //FILTER ITEM REMOVING CORRUPTED DATA
            if(!country.isEmpty())
                countries.add(new CountryData(active,deaths,country, countryDeaths));

        return countries;
    }
}
