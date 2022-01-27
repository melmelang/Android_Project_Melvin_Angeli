package be.ehb.boodschapen.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import be.ehb.boodschapen.R;
//import be.ehb.boodschapen.databinding.FragmentFirstBinding;
import be.ehb.boodschapen.model.Bood;
import be.ehb.boodschapen.model.BoodViewModel;
import be.ehb.boodschapen.ui.MainActivity;
import be.ehb.boodschapen.ui.util.BoodAdaptor;

public class HomeFragment extends Fragment {

    private FragmentActivity mContext;
    private FloatingActionButton fab;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (FragmentActivity) context;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BoodAdaptor adaptor = new BoodAdaptor(new ArrayList<>());
        SearchView search = mContext.findViewById(R.id.sv_searchTitle);

        BoodViewModel model = new ViewModelProvider(mContext).get(BoodViewModel.class);
        model.getSharedBoods().observe(getViewLifecycleOwner(), boods -> {
            adaptor.reloadBood(boods);
        });

        RecyclerView boodRV = view.findViewById(R.id.rv_winkard);
        boodRV.setAdapter(adaptor);
        boodRV.setLayoutManager(new GridLayoutManager(mContext, 1));

        fab = mContext.findViewById(R.id.fabCreateBoodschappenlijst);
        fab.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_createFragment);
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                model.getSearchSelectedBood(query);
                Toast.makeText(mContext, "succes", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                model.getSearchSelectedBood(newText);
                return true;
            }
        });
    }

}