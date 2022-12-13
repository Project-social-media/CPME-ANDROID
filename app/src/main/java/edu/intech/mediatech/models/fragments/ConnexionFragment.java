package edu.intech.mediatech.models.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class ConnexionFragment extends Fragment {


    FragmentConnexionBinding binding;

    public ConnexionFragment() throws IOException {
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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.connexionConnectBtn.setOnClickListener(v -> {
            String email = binding.connexionUserBox.getText().toString();
            String password = binding.connexionPasswordBox.getText().toString();

            User u = new User(email, password);

            UserRepository.getInstance().authenticateUser(u).observe(getViewLifecycleOwner(), user -> {
                if (user != null) {
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
    }
}