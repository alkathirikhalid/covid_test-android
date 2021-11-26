package com.iquii.covidtest.model.network.parser;

import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.network.Response;

import org.json.JSONException;

import java.util.ArrayList;

public interface Parser {

    ArrayList<CountryData> parse(Response response) throws JSONException;
}
