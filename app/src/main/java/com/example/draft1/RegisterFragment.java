package com.example.draft1;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.draft1.databinding.FragmentRegisterBinding;
import com.example.draft1.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

public class RegisterFragment extends Fragment {



    private FirebaseAuth mAuth;

    private FragmentRegisterBinding binding;
    SharedPreferences mPrefs;
    User user;
    private Object btnRegister;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        mPrefs = getActivity().getSharedPreferences(getString(R.string.userPref), MODE_PRIVATE);

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_register_to_FirstFragment);

            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateDAta()) {
                    NavHostFragment.findNavController(RegisterFragment.this)
                            .navigate(R.id.action_register_to_Home);
                    getActivity().finish();
                }

            }
        });
    }

    private boolean validateDAta() {
        String email = binding.editTextTextEmailAddress.getText().toString();
        String name = binding.editTextTextPersonName.getText().toString();
        String password = binding.editTextTextPassword2.getText().toString();
        String confirmPassword = binding.editTextTextPassword3.getText().toString();

        if (name.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || confirmPassword.isEmpty()) {

            Toast.makeText(getContext(), "All fields are Required.", Toast.LENGTH_SHORT).show();
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getContext(), "Email not valid.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() < 6 || confirmPassword.length() < 6) {
            Toast.makeText(getContext(), "password must be 6 characters long.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(getContext(), "password should match confirm password.", Toast.LENGTH_SHORT).show();
            return false;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new  OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(name, email, password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                Toast.makeText(getContext(), "Successfully logged in", Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(getContext(), "Failed to register! Try Again!", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                        }

                    }

                });

        user = new User(name, email, password);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(getString(R.string.userdata), json);
        prefsEditor.commit();

        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();


        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
