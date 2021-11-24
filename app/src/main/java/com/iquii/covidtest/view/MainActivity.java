package com.iquii.covidtest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.iquii.covidtest.R;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.network.ConnectionManager;
import com.iquii.covidtest.model.network.Parser;
import com.iquii.covidtest.model.network.Response;
import com.iquii.covidtest.utils.Constants;

import org.json.JSONException;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new NetworkAsyncTask().execute(Constants.URL);
    }




    private class NetworkAsyncTask extends AsyncTask<String,Void, ArrayList<CountryData>>{


        @Override
        protected ArrayList<CountryData> doInBackground(String... strings) {

            Response response = ConnectionManager.connect(strings[0]);
            if (response.isSuccesful()){
                try {
                    return Parser.parse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new ArrayList<CountryData>();
                }
            }
            return  new ArrayList<CountryData>();
        }

        @Override
        protected void onPostExecute(ArrayList<CountryData> countryData) {

            for(CountryData country : countryData){
                Log.d(TAG, country.toString());
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArray(Constants.ARG_LIST, countryData.toArray(new CountryData[0]));
            Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment).setGraph(R.navigation.main_navigation,bundle);


        }
    }

}