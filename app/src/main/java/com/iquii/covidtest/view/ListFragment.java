package com.iquii.covidtest.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.iquii.covidtest.R;
import com.iquii.covidtest.controller.DetailController;
import com.iquii.covidtest.controller.ListController;
import com.iquii.covidtest.databinding.FragmentListBinding;
import com.iquii.covidtest.model.DataAccessLayer;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.model.observer.CountryObserver;
import com.iquii.covidtest.utils.Constants;
import com.iquii.covidtest.view.adapter.CountryListAdapter;

import java.util.ArrayList;


public class ListFragment extends Fragment implements ListView, CountryObserver {

    private FragmentListBinding binding;
    private ListController controller = new ListController();
    private DataAccessLayer model = new DataAccessLayer();

    private SearchView searchView;
    private CountryListAdapter adapter;
    private final int SORT_ACTIVE = 0;
    private final int SORT_DEATHS = 1;
    private final int SORT_ACTIVE_RATIO = 2;
    private final int SORT_DEATHS_RATIO = 3;

    @Override
    public void onStart() {
        super.onStart();
        model.register(this);
        controller.bind(this, model);
    }

    @Override
    public void onStop() {
        super.onStop();
        controller.unbind();
        model.unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                controller.search();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sort) {
            PopupMenu popupMenu = new PopupMenu(requireContext(), getActivity().findViewById(R.id.sort));
            popupMenu.getMenu().add(0, SORT_ACTIVE, 0, getString(R.string.sort_active));
            popupMenu.getMenu().add(0, SORT_DEATHS, 0, getString(R.string.sort_deaths));
            popupMenu.getMenu().add(0, SORT_ACTIVE_RATIO, 0, getString(R.string.sort_active_ratio));
            popupMenu.getMenu().add(0, SORT_DEATHS_RATIO, 0, getString(R.string.sort_deaths_ratio));

            popupMenu.setOnMenuItemClickListener(this::onClickMenu);
            popupMenu.show();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private boolean onClickMenu(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case SORT_ACTIVE:
                controller.sortByActive();
                break;
            case SORT_DEATHS:
                controller.sortByDeaths();
                break;
            case SORT_ACTIVE_RATIO:
                controller.sortByActiveRatio();
                break;
            case SORT_DEATHS_RATIO:
                controller.sortByDeathsRatio();
                break;
            default: break;
        }

        return false;
    }

    @Override
    public void renderList(ArrayList<CountryData> data) {
        adapter = new CountryListAdapter(item -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.ARG_DETAIL,item);
            Navigation.findNavController(binding.getRoot()).navigate(R.id.detailFragment, bundle);
        }, data);
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void loader(boolean isEnabled) {
        //TODO: insert loader
    }

    @Override
    public CharSequence getSearchInput() {
        return searchView.getQuery();
    }
}
