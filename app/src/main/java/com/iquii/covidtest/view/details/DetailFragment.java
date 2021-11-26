package com.iquii.covidtest.view.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iquii.covidtest.controller.DetailController;
import com.iquii.covidtest.databinding.FragmentDetailBinding;
import com.iquii.covidtest.model.DataAccessLayer;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.entity.DataPoint;
import com.iquii.covidtest.model.observer.HistoryObserver;

import java.util.ArrayList;

public class DetailFragment extends Fragment implements DetailView, HistoryObserver {

    private FragmentDetailBinding binding;
    private DetailController controller = new DetailController();
    private DataAccessLayer model = DataAccessLayer.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model.register(this);
        controller.bind(this, model);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        controller.unbind();
        model.unregister(this);
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
        controller.init();
    }

    @Override
    public void renderCountry(CountryData data) {
        if(binding != null){
            binding.countryName.setText(data.getCountry());
            binding.activeCases.setText(Integer.toString(data.getActive()));
            binding.deaths.setText(Integer.toString(data.getDeaths()));
            binding.casesRatio.setText(String.format("%.2f", data.getActiveRatio()));
            binding.deathsRatio.setText(String.format("%.2f", data.getDeathsRatio()));
        }
    }

    @Override
    public void renderChart(ArrayList<DataPoint> data) {
        binding.graphView.setData(data);
    }
}
