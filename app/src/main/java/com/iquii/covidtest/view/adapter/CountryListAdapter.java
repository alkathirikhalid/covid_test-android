package com.iquii.covidtest.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iquii.covidtest.databinding.ListItemBinding;
import com.iquii.covidtest.model.entity.CountryData;

import java.util.ArrayList;


public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryHolder> {

    public CountryListAdapter(OnCountryClickListener onCountryClickListener, ArrayList<CountryData> countryData) {
        this.onCountryClickListener = onCountryClickListener;
        this.countryDataList.addAll(countryData);
    }

    private OnCountryClickListener onCountryClickListener;
    private ArrayList<CountryData> countryDataList = new ArrayList<>();

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


    /*@Override
    public Filter getFilter() {
        return countryFilter;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CountryData> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(countryDataListFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(CountryData item : countryDataList){
                    if(item.getCountry().toLowerCase().startsWith(filterPattern)){
                        filteredList.add(item);

                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countryDataList.clear();
            countryDataList.addAll((Collection<? extends CountryData>) results.values);
            notifyDataSetChanged();
        }
    };*/

    class CountryHolder extends RecyclerView.ViewHolder {

        public CountryHolder(@NonNull ListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            listItemBinding = itemBinding;
        }

        private ListItemBinding listItemBinding;

        public void bind(CountryData item){
            listItemBinding.tvCountry.setText(item.getCountry());
            listItemBinding.card.setOnClickListener(v -> {
                onCountryClickListener.onCountryClick(item);
            }) ;
        }
    }
}

