package com.iquii.covidtest.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class CountryDeaths implements Parcelable {

    public CountryDeaths(String date, int quantity) {
        this.date = date;
        this.quantity = quantity;
    }

    private String date;
    private int quantity;

    public String getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeInt(this.quantity);
    }

    public void readFromParcel(Parcel source) {
        this.date = source.readString();
        this.quantity = source.readInt();
    }

    protected CountryDeaths(Parcel in) {
        this.date = in.readString();
        this.quantity = in.readInt();
    }

    public static final Creator<CountryDeaths> CREATOR = new Creator<CountryDeaths>() {
        @Override
        public CountryDeaths createFromParcel(Parcel source) {
            return new CountryDeaths(source);
        }

        @Override
        public CountryDeaths[] newArray(int size) {
            return new CountryDeaths[size];
        }
    };
}
