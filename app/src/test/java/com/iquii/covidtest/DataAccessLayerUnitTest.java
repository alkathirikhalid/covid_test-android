package com.iquii.covidtest;

import static org.junit.Assert.assertTrue;

import com.iquii.covidtest.model.DataAccessLayer;
import com.iquii.covidtest.model.entity.CountryData;

import org.junit.Test;

import java.util.ArrayList;


public class DataAccessLayerUnitTest {

    @Test
    public void testPerformCases() {

        DataAccessLayer accessLayer = DataAccessLayer.getInstance();

        ArrayList<CountryData> data = accessLayer.performCases();

        assertTrue(data.size() > 0);
    }
}