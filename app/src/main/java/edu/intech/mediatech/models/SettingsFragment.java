package edu.intech.mediatech.models;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.intech.mediatech.R;
import edu.intech.mediatech.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.dataRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.subs_radio:
                    //print "subs"
                    DashboardFragment.dashboardDataType = "subs";
                    break;
                case R.id.likes_radio:
                    // do operations specific to this selection
                    DashboardFragment.dashboardDataType = "likes";
                    break;
                case R.id.views_radio:
                    // do operations specific to this selection
                    DashboardFragment.dashboardDataType = "views";
                    break;
                case R.id.shares_radio:
                    // do operations specific to this selection
                    DashboardFragment.dashboardDataType = "shares";
                    break;
            }
        });

        binding.activateDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The toggle is enabled
                Log.d("DarkMode", "Dark mode is enabled");
            } else {
                // The toggle is disabled
                Log.d("DarkMode", "Dark mode is disabled");
            }
        });
    }
}