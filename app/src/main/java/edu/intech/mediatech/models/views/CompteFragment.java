package edu.intech.mediatech.models.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.intech.mediatech.R;
import edu.intech.mediatech.databinding.FragmentCompteBinding;
import edu.intech.mediatech.models.bdd.User;
import edu.intech.mediatech.viewmodels.UserViewModel;
import kotlin.text.Regex;

public class CompteFragment extends Fragment {

    FragmentCompteBinding binding;
    UserViewModel userViewModel;
    SharedPreferences sharedPref;
    Regex regex = new Regex("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");


    public CompteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompteBinding.inflate(inflater, container, false);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPref = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);

        binding.emailTb.setHint(sharedPref.getString("user_email", null));
        binding.usernameTb.setHint(sharedPref.getString("user_username", null));


        binding.updateUserTb.setOnClickListener(v -> {
            User newUser = new User("", "", "");
            String newUsername = binding.usernameTb.getText().toString().trim();
            String newEmail = binding.emailTb.getText().toString().trim().isEmpty() ? sharedPref.getString("user_email", null) : binding.emailTb.getText().toString().trim(); ;
            String newPassword = binding.passwordTb.getText().toString().trim();
            String passwordConfirm = binding.confirmTb.getText().toString().trim();

            if (newUsername.isEmpty() && newEmail.isEmpty() && newPassword.isEmpty() && passwordConfirm.isEmpty()) {
                Toast.makeText(getContext(), "You have not modified anything", Toast.LENGTH_SHORT).show();
                return;
            }

            if (newPassword.equals(passwordConfirm)) {
                if (regex.matches(newEmail)) {
                    newUser.setUsername(newUsername);
                    newUser.setEmail(newEmail);
                    newUser.setPassword(newPassword);
                    userViewModel.updateUser(sharedPref.getString("user_email", null), newUser);
                    Toast.makeText(getContext(), "User updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Email is not valid", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Passwords are not the same", Toast.LENGTH_SHORT).show();
            }
        });

    }
}