package edu.intech.mediatech.models.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

import edu.intech.mediatech.R;
import edu.intech.mediatech.databinding.FragmentConnexionBinding;
import edu.intech.mediatech.models.DashboardActivity;
import edu.intech.mediatech.models.bdd.User;
import edu.intech.mediatech.repositories.UserRepository;
import edu.intech.mediatech.viewmodels.UserViewModel;

public class ConnexionFragment extends Fragment {


    FragmentConnexionBinding binding;
    UserViewModel userViewModel;
    SharedPreferences sharedPref;


    public ConnexionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConnexionBinding.inflate(inflater, container, false);
        this.userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPref = getContext().getSharedPreferences("preferences", MODE_PRIVATE);
        String username = sharedPref.getString("user_username", "");
        String pass = sharedPref.getString("user_password", "");

        binding.connexionConnectBtn.setOnClickListener(v -> {
            String email = binding.connexionUserBox.getText().toString();
            String password = binding.connexionPasswordBox.getText().toString();

            User u = new User(email, password);

            userViewModel.authenticateUser(u).observe(getViewLifecycleOwner(), user -> {
                if (user != null) {
                    Toast.makeText(getContext(), "Connexion réussie", Toast.LENGTH_SHORT).show();
                    binding.connexionUserBox.setText("");
                    binding.connexionPasswordBox.setText("");
                    sharedPref.edit().putString("user_username", email).apply();
                    sharedPref.edit().putString("user_password", password).apply();
                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Connexion échouée", Toast.LENGTH_SHORT).show();
                }
            });
        });

        binding.connexionForgotPwdBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_connexionFragment_to_lostFragmentPassword);
        });

        //if user credentials are stored in shared preferences, we log him in
        if (!username.equals("") && !pass.equals("")) {
            binding.connexionUserBox.setText(username);
            binding.connexionPasswordBox.setText(pass);
            binding.connexionConnectBtn.performClick();
        }
    }
}