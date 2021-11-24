package com.iquii.covidtest.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;

import com.iquii.covidtest.R;
import com.iquii.covidtest.databinding.FragmentListBinding;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.utils.Constants;
import com.iquii.covidtest.view.adapter.CountryListAdapter;
import com.iquii.covidtest.view.adapter.OnCountryClickListener;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = FragmentListBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CountryData[] data = (CountryData[]) requireArguments().getParcelableArray(Constants.ARG_LIST);
        CountryListAdapter adapter = new CountryListAdapter(item -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.ARG_DETAIL,item);
            Navigation.findNavController(view).navigate(R.id.detailFragment, bundle);
        }, data);
        binding.recycler.setAdapter(adapter);
    }
}
