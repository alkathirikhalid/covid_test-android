package com.iquii.covidtest.model.observer;

import com.iquii.covidtest.model.entity.CountryData;

import java.util.ArrayList;

public interface CountryObserver extends DomainObserver {

    void loader(boolean isEnabled);

    void renderList(ArrayList<CountryData> data);
}
