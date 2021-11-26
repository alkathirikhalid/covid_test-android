package com.iquii.covidtest;

import static org.junit.Assert.assertEquals;

import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.entity.CountryDeaths;

import org.junit.Test;

import java.util.ArrayList;


public class CountryUnitTest {

    @Test
    public void testModelFields() {

        ArrayList<CountryDeaths> deaths = new ArrayList<CountryDeaths>();
        deaths.add(new CountryDeaths("01/01/01", 1000));
        deaths.add(new CountryDeaths("01/01/01", 1000));
        deaths.add(new CountryDeaths("01/01/01", 1000));

        ArrayList<CountryData> data = new ArrayList<CountryData>();
        data.add(new CountryData(0, 5000, "USA", deaths));
        data.add(new CountryData(1, 2000, "UK", deaths));
        data.add(new CountryData(200, 1000, "Spain", deaths));
        data.add(new CountryData(5, 10000, "Italy", deaths));

        assertEquals(data.size(), 4);
        assertEquals(data.get(0).getCountry(), "USA");
        assertEquals(data.get(0).getActive(), 0);
        assertEquals(data.get(0).getCountryDeathsList().size(), 3);
        assertEquals(data.get(0).getCountry(), "USA");
    }
}