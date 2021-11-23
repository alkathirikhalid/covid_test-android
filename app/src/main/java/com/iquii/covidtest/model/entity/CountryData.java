package com.iquii.covidtest.model.entity;

public class CountryData {

    private int active;
    private int deaths;
    private String country;
    private int population;
    private String lat;
    private String lng;
    private String updated;

    public CountryData(int active, int deaths, String country, int population, String lat, String lng, String updated) {
        this.active = active;
        this.deaths = deaths;
        this.country = country;
        this.population = population;
        this.lat = lat;
        this.lng = lng;
        this.updated = updated;
    }


    public CountryData(int active, int deaths, String country, int population,  String updated) {
        this.active = active;
        this.deaths = deaths;
        this.country = country;
        this.population = population;
        this.lat = null;
        this.lng = null;
        this.updated = updated;
    }


    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
