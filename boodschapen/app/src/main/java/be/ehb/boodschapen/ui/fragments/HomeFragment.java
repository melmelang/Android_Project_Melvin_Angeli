package be.ehb.boodschapen.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import be.ehb.boodschapen.R;
import be.ehb.boodschapen.model.BoodViewModel;
import be.ehb.boodschapen.ui.util.BoodAdaptor;

public class HomeFragment extends Fragment {

    private FragmentActivity mContext;

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
        model.getSharedBoods().observe(getViewLifecycleOwner(), adaptor::reloadBood);

        RecyclerView boodRV = view.findViewById(R.id.rv_winkard);
        boodRV.setAdapter(adaptor);
        boodRV.setLayoutManager(new GridLayoutManager(mContext, 1));

        FloatingActionButton fab = mContext.findViewById(R.id.fabCreateBoodschappenlijst);
        fab.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_createFragment));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(mContext, "succes", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

}