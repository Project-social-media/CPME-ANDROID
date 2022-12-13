package edu.intech.mediatech.models.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

import edu.intech.mediatech.R;
import edu.intech.mediatech.databinding.FragmentConnexionBinding;
import edu.intech.mediatech.databinding.FragmentLostPasswordBinding;
import kotlin.text.Regex;


public class LostFragmentPassword extends Fragment {

    FragmentLostPasswordBinding binding;
    Regex regex = new Regex("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public LostFragmentPassword() {
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
        binding = FragmentLostPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fgtPwdBackBtn.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });

        binding.fgtPwdValidateBtn.setOnClickListener(v -> {
            String email = binding.fgtPwdEmailBox.getText().toString();

            if (regex.matches(email)) {
                Toast.makeText(getContext(), "Email envoyé", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.action_lostFragmentPassword_to_connexionFragment);

            } else {
                Toast.makeText(getContext(), "Email invalide", Toast.LENGTH_SHORT).show();
            }
        });
    }
}