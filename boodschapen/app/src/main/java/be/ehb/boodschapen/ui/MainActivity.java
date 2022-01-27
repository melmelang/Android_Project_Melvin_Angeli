package be.ehb.boodschapen.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import be.ehb.boodschapen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}