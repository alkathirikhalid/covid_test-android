package com.iquii.covidtest.model.network;

import android.os.AsyncTask;

import com.iquii.covidtest.controller.GetData;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.network.parser.ListParser;
import com.iquii.covidtest.model.network.parser.Parser;

import org.json.JSONException;

import java.util.ArrayList;

public class NetworkAsyncTask extends AsyncTask<String, Void, ArrayList<CountryData>> {

    private GetData getData;
    private Parser parser;

    public NetworkAsyncTask(GetData getData, Parser parser) {
        this.getData = getData;
        this.parser = parser;
    }

    @Override
    protected ArrayList<CountryData> doInBackground(String... strings) {

        Response response = ConnectionManager.connect(strings[0]);
        if (response.isSuccesful()) {
            try {
                return parser.parse(response);
            } catch (JSONException e) {
                e.printStackTrace();
                return new ArrayList<CountryData>();
            }
        }
        return new ArrayList<CountryData>();
    }

    @Override
    protected void onPostExecute(ArrayList<CountryData> countryData) {
        getData.getData(countryData);
    }
}
