package be.ehb.boodschapen.ui.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import org.threeten.bp.LocalDate;

import be.ehb.boodschapen.R;
import be.ehb.boodschapen.model.Bood;
import be.ehb.boodschapen.model.BoodViewModel;
import be.ehb.boodschapen.model.util.DateConverter;

public class CreateFragment extends Fragment {

    private EditText etTitle, etdDay, etdMonth, etdYear, etmlDescription, etmlBlist;
    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bood bood = new Bood();

        Button btnOk = view.findViewById(R.id.b_ok);
        Button btnReturn = view.findViewById(R.id.btn_create_return);
        etTitle = view.findViewById(R.id.et_title);
        etdDay = view.findViewById(R.id.etd_day);
        etdMonth = view.findViewById(R.id.etd_month);
        etdYear = view.findViewById(R.id.etd_jear);
        etmlDescription = view.findViewById(R.id.etml_description);
        etmlBlist = view.findViewById(R.id.etml_blist);

//        etdDay.setText(LocalDate.now().getDayOfMonth());

        btnOk.setOnClickListener(v -> {
            try {
                bood.setTitle(etTitle.getText().toString());
                bood.setDescription(etmlDescription.getText().toString());
                bood.setContent(etmlBlist.getText().toString());
                bood.setCreatingDate(LocalDate.now());
                bood.setMakeBoodschappenDate(DateConverter.stringToDate(etdYear.getText().toString() + "-"
                        + etdMonth.getText().toString() + "-"
                        + etdDay.getText().toString()));
                BoodViewModel boodViewModel = new ViewModelProvider(requireActivity()).get(BoodViewModel.class);
                boodViewModel.insertBood(bood);
                Toast.makeText(mContext, "succes", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigateUp();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        btnReturn.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());
    }


}