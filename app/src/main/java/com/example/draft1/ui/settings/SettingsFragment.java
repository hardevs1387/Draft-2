package com.example.draft1.ui.settings;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.draft1.R;
import com.example.draft1.databinding.FragmentSettingsBinding;
import com.example.draft1.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    SharedPreferences mPrefs;
    BottomNavigationView navView;
    private Button btnShop;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        mPrefs = getActivity().getSharedPreferences(getString(R.string.userPref), MODE_PRIVATE);
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        //navView = getActivity().findViewById(R.id.nav_view);
        //navView.setVisibility(View.GONE);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Gson gson = new Gson();
        String json = mPrefs.getString(getString(R.string.userdata), "");
        User obj = gson.fromJson(json, User.class);

        binding.btnName.setText(obj.getName());

        binding.menuHeader.settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getFragmentManager().popBackStack();
            }
        });

        binding.btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString(getString(R.string.userdata), null);
                prefsEditor.commit();

                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.mainActivity);

                getActivity().finish();
            }
        });

        binding.menuHeader.tokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.navigation_fedcoin);
            }
        });

        binding.btnAccountInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.navigation_profile);
            }
        });

        binding.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.navigation_location_sharing);
            }
        });

        binding.btnTokenFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.navigation_fedcoin);
            }
        });

        binding.btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://fedstore.federation.edu.au/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        binding.btnContribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.navigation_contribution);
            }
        });

        binding.btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.navigation_notitfication);
            }
        });
        binding.btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.navigation_about);
            }
        });

        binding.btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.navigation_help);
            }
        });

        binding.btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.navigation_privacy);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //navView.setVisibility(View.VISIBLE);
        binding = null;
    }

}

