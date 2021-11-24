package com.iquii.covidtest.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iquii.covidtest.databinding.FragmentDetailBinding;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.utils.Constants;

public class DetailFragment extends Fragment {

    FragmentDetailBinding binding;

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
        binding.countryName.setText(data.getCountry());
        binding.activeCases.setText(Integer.toString(data.getActive()));
        binding.deaths.setText(Integer.toString(data.getDeaths()));
        binding.casesRatio.setText(String.format("%.2f", data.getActiveRatio));
        binding.deathsRatio.setText(String.format("%.2f", data.getDeathRatio));

    }
}
