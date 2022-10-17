package com.example.draft1;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.draft1.databinding.FragmentLoginBinding;
import com.example.draft1.models.User;
import com.google.gson.Gson;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    SharedPreferences mPrefs;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        mPrefs = getActivity().getSharedPreferences(getString(R.string.userPref), MODE_PRIVATE);

        binding = FragmentLoginBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_login_to_FirstFragment);

            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateDAta()) {
                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.action_login_to_Home);

                    getActivity().finish();
                }

            }
        });

    }

    private boolean validateDAta() {

        String email = binding.editTextTextEmailAddress.getText().toString();
        String password = binding.editTextTextPassword2.getText().toString();

        if (email.isEmpty()
                || password.isEmpty()) {

            Toast.makeText(getContext(), "Please Enter Email and password correctly", Toast.LENGTH_SHORT).show();

            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getContext(), "Email not valid.", Toast.LENGTH_SHORT).show();
            return false;
        }

        Gson gson = new Gson();
        String json = mPrefs.getString(getString(R.string.userdata), "");
        User obj = gson.fromJson(json, User.class);

        if (obj == null) {
            Toast.makeText(getContext(), "No user found on this device", Toast.LENGTH_SHORT).show();
            return false;

        } else if (!obj.getEmail().equals(binding.editTextTextEmailAddress.getText().toString())
                || !obj.getPassword().equals(binding.editTextTextPassword2.getText().toString())) {

            Toast.makeText(getContext(), "Email or password is incorrect", Toast.LENGTH_SHORT).show();
            return false;

        } else if (obj.getEmail().equals(binding.editTextTextEmailAddress.getText().toString())
                || obj.getPassword().equals(binding.editTextTextPassword2.getText().toString())) {

            Toast.makeText(getContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
