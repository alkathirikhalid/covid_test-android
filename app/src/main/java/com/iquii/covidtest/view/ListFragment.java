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
import com.iquii.covidtest.databinding.FragmentListBinding;
import com.iquii.covidtest.model.entity.CountryData;
import com.iquii.covidtest.utils.Constants;
import com.iquii.covidtest.view.adapter.CountryListAdapter;


public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private CountryListAdapter adapter;
    private final int SORT_ACTIVE = 0;
    private final int SORT_DEATHS = 1;
    private final int SORT_ACTIVE_RATIO = 2;
    private final int SORT_DEATHS_RATIO = 3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.sort:
                PopupMenu popupMenu = new PopupMenu(requireContext(), binding.title);
                popupMenu.getMenu().add(0, SORT_ACTIVE, 0, getString(R.string.sort_active));
                popupMenu.getMenu().add(0, SORT_DEATHS, 0, getString(R.string.sort_deaths));
                popupMenu.getMenu().add(0, SORT_ACTIVE_RATIO, 0, getString(R.string.sort_active_ratio));
                popupMenu.getMenu().add(0, SORT_DEATHS_RATIO, 0, getString(R.string.sort_deaths_ratio));

                popupMenu.setOnMenuItemClickListener(this::onClickMenu);
                popupMenu.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private boolean onClickMenu(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case SORT_ACTIVE:
                adapter.sortByActive();
                break;
            case SORT_DEATHS:
                adapter.sortByDeaths();
                break;
            case SORT_ACTIVE_RATIO:
                adapter.sortByActiveRatio();
                break;
            case SORT_DEATHS_RATIO:
                adapter.sortByDeathsRatio();
                break;
            default: break;
        }

        return false;
    }

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
         adapter = new CountryListAdapter(item -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.ARG_DETAIL,item);
            Navigation.findNavController(view).navigate(R.id.detailFragment, bundle);
        }, data);
        binding.recycler.setAdapter(adapter);
        binding.title.setOnClickListener(v -> {
            adapter.sortByActive();
        });
    }
}
