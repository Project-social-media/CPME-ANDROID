package edu.intech.mediatech.models.views;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import edu.intech.mediatech.databinding.FragmentDashboardBinding;
import edu.intech.mediatech.repositories.StatsRepository;
import edu.intech.mediatech.viewmodels.UserViewModel;


public class DashboardFragment extends Fragment {

    FragmentDashboardBinding binding;
    SharedPreferences sharedPref;
    UserViewModel userViewModel;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        this.userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPref = getContext().getSharedPreferences("preferences", MODE_PRIVATE);
        Log.d("tokentesmorts", "onViewCreated: " + sharedPref.getString("user_token", ""));
        String token = sharedPref.getString("user_token", null);
        LiveData<Map<String, Integer>> listData = StatsRepository.getInstance().getTwitterUserPostStats(token);

        //print in console list
        listData.observe(getViewLifecycleOwner(), stats -> {
            if (sharedPref.getString("user_dashboard_options", "subs").equals("subs")) {
                binding.dp1Type.setText("(Abonnés)");
            } else if (sharedPref.getString("user_dashboard_options", "subs").equals("views")) {
                binding.dp1Type.setText("(Vues)");
                binding.twtFollowers.setText(String.valueOf(stats.get("viewsCount")));
            } else if (sharedPref.getString("user_dashboard_options", "subs").equals("likes")) {
                binding.dp1Type.setText("(Likes)");
                binding.twtFollowers.setText(String.valueOf(stats.get("likesCount")));
            } else {
                binding.dp1Type.setText("(Partages)");
                binding.twtFollowers.setText(String.valueOf(stats.get("sharesCount")));
            }

        });
    }
}