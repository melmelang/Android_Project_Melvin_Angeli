package be.ehb.boodschapen.ui.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.room.Delete;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import be.ehb.boodschapen.R;
import be.ehb.boodschapen.model.Bood;
import be.ehb.boodschapen.model.BoodDb;
import be.ehb.boodschapen.model.BoodViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    private BoodDb mDb;
    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (FragmentActivity) context;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bood givenBood = (getArguments() != null)? (Bood) getArguments().getSerializable("boodschapItem") : null;
        if (givenBood != null){
            TextView TVTitle = view.findViewById(R.id.tv_detail_title);
            TextView TVCreatedDate = view.findViewById(R.id.tv_detail_created_at);
            TextView TVDescription = view.findViewById(R.id.tv_detail_description);
            TextView TVBDatum = view.findViewById(R.id.tv_detail_bdatum);
            TextView TVList = view.findViewById(R.id.tv_detail_List);
            Button BTNReturn = view.findViewById(R.id.btn_detail_return);
            Button BTNDelete = view.findViewById(R.id.btn_detail_delete);

//            long id = givenBood.getId();

            TVTitle.setText(givenBood.getTitle());
            TVCreatedDate.setText(givenBood.getCreatingDate().toString());
            TVDescription.setText(givenBood.getDescription());
            TVBDatum.setText(givenBood.getMakeBoodschappenDate().toString());
            TVList.setText(givenBood.getContent());

            BTNReturn.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

            BTNDelete.setOnClickListener(v -> {
                BoodViewModel boodViewModel = new ViewModelProvider(getActivity()).get(BoodViewModel.class);
                boodViewModel.deleteBood(givenBood);
                Toast.makeText(mContext, "succes", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigateUp();
            });
        }
    }
}