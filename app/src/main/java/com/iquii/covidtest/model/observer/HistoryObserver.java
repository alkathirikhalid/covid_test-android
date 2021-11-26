package com.iquii.covidtest.model.observer;

import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.entity.DataPoint;

import java.util.ArrayList;

public interface HistoryObserver extends DomainObserver {

    void renderCountry(CountryData data);

    void renderChart(ArrayList<DataPoint> data);
}
