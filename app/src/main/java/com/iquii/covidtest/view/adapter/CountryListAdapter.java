package com.iquii.covidtest.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iquii.covidtest.databinding.ListItemBinding;
import com.iquii.covidtest.model.entity.CountryData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryHolder> {

    public CountryListAdapter(OnCountryClickListener onCountryClickListener, CountryData[] countryDataList) {
        this.onCountryClickListener = onCountryClickListener;
        this.countryDataList = Arrays.asList(countryDataList);
    }

    OnCountryClickListener onCountryClickListener;
    List<CountryData> countryDataList;

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryHolder(ListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        holder.bind(countryDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return countryDataList.size();
    }

    public void sortByActive(){
        countryDataList.sort(Comparator.comparingInt(CountryData::getActive).reversed());
        notifyDataSetChanged();
    }

    public void sortByDeaths(){
        countryDataList.sort(Comparator.comparingInt(CountryData::getDeaths).reversed());
        notifyDataSetChanged();
    }

    public void sortByActiveRatio(){
        countryDataList.sort(Comparator.comparingDouble(CountryData::getActiveRatio).reversed());
        notifyDataSetChanged();
    }

    public void sortByDeathsRatio(){
        countryDataList.sort(Comparator.comparingDouble(CountryData::getDeathsRatio).reversed());
        notifyDataSetChanged();
    }

    class CountryHolder extends RecyclerView.ViewHolder {

        public CountryHolder(@NonNull ListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            listItemBinding = itemBinding;
        }

        private ListItemBinding listItemBinding;

        public void bind(CountryData item){

            listItemBinding.labelName.setText(item.getCountry());
            listItemBinding.card.setOnClickListener(v -> {
                onCountryClickListener.onCountryClick(item);
            }) ;
        }
    }
}

