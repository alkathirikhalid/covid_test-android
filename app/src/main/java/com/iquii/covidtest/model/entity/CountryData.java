package com.iquii.covidtest.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CountryData implements Parcelable {


    private int active;
    private int deaths;
    private String country;
    private double activeRatio;
    private double deathsRatio;
    private List<CountryDeaths> countryDeathsList;


    public CountryData(int active, int deaths, String country, List<CountryDeaths> countryDeathsList) {
        this.active = active;
        this.deaths = deaths;
        this.country = country;
        this.countryDeathsList = countryDeathsList;
        activeRatio = (double) this.active /100000;
        deathsRatio = (double) this.deaths /100000;
    }


    public int getActive() {
        return active;
    }


    public int getDeaths() {
        return deaths;
    }



    public String getCountry() {
        return country;
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
        dest.writeDouble(this.activeRatio);
        dest.writeDouble(this.deathsRatio);
        dest.writeTypedList(this.countryDeathsList);
    }

    public void readFromParcel(Parcel source) {
        this.active = source.readInt();
        this.deaths = source.readInt();
        this.country = source.readString();
        this.activeRatio = source.readDouble();
        this.deathsRatio = source.readDouble();
        this.countryDeathsList = source.createTypedArrayList(CountryDeaths.CREATOR);
    }

    protected CountryData(Parcel in) {
        this.active = in.readInt();
        this.deaths = in.readInt();
        this.country = in.readString();
        this.activeRatio = in.readDouble();
        this.deathsRatio = in.readDouble();
        this.countryDeathsList = in.createTypedArrayList(CountryDeaths.CREATOR);
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
