package com.iquii.covidtest.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class CountryData implements Parcelable {


    private int active;
    private int deaths;
    private String country;
    private int population;
    private double activeRatio;
    private double deathsRatio;


    public CountryData(int active, int deaths, String country, int population) {
        this.active = active;
        this.deaths = deaths;
        this.country = country;
        this.population = population;
        activeRatio = (double) this.active /100000;
        deathsRatio = (double) this.deaths /100000;
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

    public double getActiveRatio() {
        return activeRatio;
    }

    public double getDeathsRatio() {
        return deathsRatio;
    }

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
        dest.writeDouble(this.activeRatio);
        dest.writeDouble(this.deathsRatio);
    }

    public void readFromParcel(Parcel source) {
        this.active = source.readInt();
        this.deaths = source.readInt();
        this.country = source.readString();
        this.population = source.readInt();
        this.activeRatio = source.readDouble();
        this.deathsRatio = source.readDouble();
    }

    protected CountryData(Parcel in) {
        this.active = in.readInt();
        this.deaths = in.readInt();
        this.country = in.readString();
        this.population = in.readInt();
        this.activeRatio = in.readDouble();
        this.deathsRatio = in.readDouble();
    }

    public static final Creator<CountryData> CREATOR = new Creator<CountryData>() {
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
