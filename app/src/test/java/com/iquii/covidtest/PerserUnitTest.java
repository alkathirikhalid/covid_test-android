package com.iquii.covidtest;


import static org.junit.Assert.assertEquals;

import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.network.Response;
import com.iquii.covidtest.model.network.parser.ListParser;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class PerserUnitTest {

    private Response response;

    @Before
    public void setup(){
        response = new Response(200, jsonresponse);
    }

    @Test
    public void testListParser() {

        ListParser parser = new ListParser();

        try {

            ArrayList<CountryData> result = parser.parse(response);

            assertEquals(result.size(), 2);
            assertEquals(result.get(0).getCountry(), "Afghanistan");
            assertEquals(result.get(0).getActive(), 157171);
            assertEquals(result.get(0).getDeaths(), 7307);
            assertEquals(result.get(0).getCountryDeathsList().size(), 0);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String jsonresponse = "{\n" +
            "    \"Afghanistan\": {\n" +
            "        \"All\": {\n" +
            "            \"confirmed\": 157171,\n" +
            "            \"recovered\": 0,\n" +
            "            \"deaths\": 7307,\n" +
            "            \"country\": \"Afghanistan\",\n" +
            "            \"population\": 35530081,\n" +
            "            \"sq_km_area\": 652090,\n" +
            "            \"life_expectancy\": \"45.9\",\n" +
            "            \"elevation_in_meters\": null,\n" +
            "            \"continent\": \"Asia\",\n" +
            "            \"abbreviation\": \"AF\",\n" +
            "            \"location\": \"Southern and Central Asia\",\n" +
            "            \"iso\": 4,\n" +
            "            \"capital_city\": \"Kabul\",\n" +
            "            \"lat\": \"33.93911\",\n" +
            "            \"long\": \"67.709953\",\n" +
            "            \"updated\": \"2021/11/26 15:22:32+00\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"Albania\": {\n" +
            "        \"All\": {\n" +
            "            \"confirmed\": 198292,\n" +
            "            \"recovered\": 0,\n" +
            "            \"deaths\": 3068,\n" +
            "            \"country\": \"Albania\",\n" +
            "            \"population\": 2930187,\n" +
            "            \"sq_km_area\": 28748,\n" +
            "            \"life_expectancy\": \"71.6\",\n" +
            "            \"elevation_in_meters\": null,\n" +
            "            \"continent\": \"Europe\",\n" +
            "            \"abbreviation\": \"AL\",\n" +
            "            \"location\": \"Southern Europe\",\n" +
            "            \"iso\": 8,\n" +
            "            \"capital_city\": \"Tirana\",\n" +
            "            \"lat\": \"41.1533\",\n" +
            "            \"long\": \"20.1683\",\n" +
            "            \"updated\": \"2021/11/26 15:22:32+00\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
}