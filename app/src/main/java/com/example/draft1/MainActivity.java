package com.example.draft1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.draft1.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.draft1.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mPrefs;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefs = getSharedPreferences(getString(R.string.userPref), MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(getString(R.string.userdata), "");

        User obj = gson.fromJson(json, User.class);

        if (obj != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {

            ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());


            getSupportActionBar().hide();

            BottomNavigationView navView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_maps, R.id.navigation_camera)
//                .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
            //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navView, navController);

            mAuth = FirebaseAuth.getInstance();
        }
    }

}