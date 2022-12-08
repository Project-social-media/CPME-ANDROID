package edu.intech.mediatech;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.intech.mediatech.databinding.FragmentConnexionBinding;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnexionFragment extends Fragment {

    FragmentConnexionBinding binding;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:3000/api")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    MyApi myApi = retrofit.create(MyApi.class);

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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.connexionConnectBtn.setOnClickListener(v -> {
            String email = binding.connexionUserBox.getText().toString();
            String password = binding.connexionPasswordBox.getText().toString();

            if (email.equals("admin") && password.equals("admin")) {
                Navigation.findNavController(v).navigate(R.id.action_connexionFragment_to_dashboardFragment);
            } else {
                Toast.makeText(getContext(), "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        binding.connexionForgotPwdBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_connexionFragment_to_lostFragmentPassword);
        });
    }


}