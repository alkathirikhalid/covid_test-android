package com.iquii.covidtest.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class CountryData implements Parcelable {

    private int active;
    private int deaths;
    private String country;
    private int population;


    public CountryData(int active, int deaths, String country, int population) {
        this.active = active;
        this.deaths = deaths;
        this.country = country;
        this.population = population;
        getActiveRatio = (double) this.active /100000;
        getDeathRatio = (double) this.deaths /100000;
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

    public double getActiveRatio;

    public double getDeathRatio;

    @Override
    public String toString() {
        return "CountryData{" +
                "active=" + active +
                ", deaths=" + deaths +
                ", country='" + country + '\'' +
                ", population=" + population +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.active);
        dest.writeInt(this.deaths);
        dest.writeString(this.country);
        dest.writeInt(this.population);
    }

    public void readFromParcel(Parcel source) {
        this.active = source.readInt();
        this.deaths = source.readInt();
        this.country = source.readString();
        this.population = source.readInt();

    }

    protected CountryData(Parcel in) {
        this.active = in.readInt();
        this.deaths = in.readInt();
        this.country = in.readString();
        this.population = in.readInt();
        getActiveRatio = (double) active/100000;
        getDeathRatio = (double)  deaths/100000;
    }

    public static final Parcelable.Creator<CountryData> CREATOR = new Parcelable.Creator<CountryData>() {
        @Override
        public CountryData createFromParcel(Parcel source) {
            return new CountryData(source);
        }

        @Override
        public CountryData[] newArray(int size) {
            return new CountryData[size];
        }
    };
}
