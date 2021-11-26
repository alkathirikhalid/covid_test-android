package com.iquii.covidtest.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iquii.covidtest.controller.DetailController;
import com.iquii.covidtest.databinding.FragmentDetailBinding;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.entity.CountryDeaths;
import com.iquii.covidtest.model.entity.DataPoint;
import com.iquii.covidtest.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetailFragment extends Fragment implements DetailView {

    FragmentDetailBinding binding;
    DetailController controller = new DetailController();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller.bind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CountryData data = requireArguments().getParcelable(Constants.ARG_DETAIL);
        controller.getCountryDates(data.getCountry());
        binding.countryName.setText(data.getCountry());
        binding.activeCases.setText(Integer.toString(data.getActive()));
        binding.deaths.setText(Integer.toString(data.getDeaths()));
        binding.casesRatio.setText(String.format("%.2f", data.getActiveRatio()));
        binding.deathsRatio.setText(String.format("%.2f", data.getDeathsRatio()));
    }

    @Override
    public void showCharts(CountryData countryData) {
        List<CountryDeaths> deaths = countryData.getCountryDeathsList();
        if(!deaths.isEmpty() && deaths.size() >= 11){
            deaths = deaths.subList(0,11);
            Collections.reverse(deaths);
            int oneMonthDeath = deaths.get(0).getQuantity();
            ArrayList<DataPoint> deathsValues = new ArrayList<>();
            for(int i = 0; i < deaths.size()-1; i++){
                int deathNumberDay = deaths.get(i+1).getQuantity() - deaths.get(i).getQuantity();
                Log.d("DEATH", Integer.toString(deathNumberDay));
                deathsValues.add(new DataPoint(i,deathNumberDay));
            }
            binding.graphView.setData(deathsValues);
        }
    }
}
