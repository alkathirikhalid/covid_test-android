package com.iquii.covidtest.model.network;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.navigation.Navigation;

import com.iquii.covidtest.R;
import com.iquii.covidtest.controller.GetData;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.utils.Constants;
import com.iquii.covidtest.view.MainActivity;

import org.json.JSONException;

import java.util.ArrayList;

public class NetworkAsyncTask extends AsyncTask<String, Void, ArrayList<CountryData>> {

    private GetData getData;

    public NetworkAsyncTask(GetData getData) {
        this.getData = getData;
    }

    @Override
    protected ArrayList<CountryData> doInBackground(String... strings) {

        Response response = ConnectionManager.connect(strings[0]);
        if (response.isSuccesful()) {
            try {
                return Parser.parse(response);
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
