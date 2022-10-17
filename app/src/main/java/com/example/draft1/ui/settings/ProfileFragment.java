package com.example.draft1.ui.settings;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.draft1.R;
import com.example.draft1.databinding.FragmentProfileBinding;
import com.example.draft1.models.User;
import com.google.gson.Gson;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    SharedPreferences mPrefs;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        mPrefs = getActivity().getSharedPreferences(getString(R.string.userPref), MODE_PRIVATE);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Gson gson = new Gson();
        String json = mPrefs.getString(getString(R.string.userdata), "");
        User obj = gson.fromJson(json, User.class);

        binding.nameTV.setText(obj.getName());

        binding.btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProfileFragment.this).popBackStack();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

