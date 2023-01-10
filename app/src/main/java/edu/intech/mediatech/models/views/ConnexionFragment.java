package edu.intech.mediatech.models.views;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
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
import edu.intech.mediatech.databinding.FragmentConnexionBinding;
import edu.intech.mediatech.models.bdd.User;
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
        String usernameCache = sharedPref.getString("user_username", "");
        String passwordCache = sharedPref.getString("user_password", "");

        binding.connexionConnectBtn.setOnClickListener(v -> {
            String usernameBoxText = binding.connexionUserBox.getText().toString();
            String passwordBoxText = binding.connexionPasswordBox.getText().toString();

            User u = new User(usernameBoxText, passwordBoxText);

            userViewModel.authenticateUser(u).observe(getViewLifecycleOwner(), token -> {
                if (token != null) {
                    Log.d("ConnexionFragment", "User authenticated: " + token);

                    userViewModel.getUserByToken(token).observe(getViewLifecycleOwner(), user -> {
                        if (user != null) {
                            Log.d("ConnexionFragment", "User found: " + user);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("user_username", user.getUsername());
                            editor.putString("user_email", user.getEmail());
                            editor.putString("user_token", token);
                            editor.apply();
                        } else {
                            Log.d("ConnexionFragment", "User not found");
                        }
                    });

                    sharedPref.edit().putString("user_password", passwordBoxText).apply();
                    Toast.makeText(getContext(), "Connexion réussie", Toast.LENGTH_SHORT).show();
                    binding.connexionUserBox.setText("");
                    binding.connexionPasswordBox.setText("");
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
        if (!usernameCache.equals("") && !passwordCache.equals("")) {
            binding.connexionUserBox.setText(usernameCache);
            binding.connexionPasswordBox.setText(passwordCache);
            binding.connexionConnectBtn.performClick();
        }
    }
}