package com.iquii.covidtest.model.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.temporal.ValueRange;

public  class ConnectionManager {

    private static final String TAG = "CONNECTION";

    public static Response connect(String path){
        StringBuilder stringBuilder  = new StringBuilder();
        int statusCode = 0;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(path);
             conn = (HttpURLConnection) url.openConnection();
// 2. Open InputStream to connection
            conn.connect();
             statusCode = conn.getResponseCode();
            boolean validStatus = ValueRange.of(200,209).isValidIntValue(statusCode);
            InputStream in;
            if(validStatus) {
               in =  conn.getInputStream();
            }
            else {
                in = conn.getErrorStream();

            }
// 3. Download and decode the string response using builder
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
           in.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }
        finally {
            if(conn != null){

                conn.disconnect();
            }
        }

        return new Response(statusCode,stringBuilder.toString());
    }
}
