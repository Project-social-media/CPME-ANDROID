package edu.intech.mediatech.models.views;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
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
    SharedPreferences sharedPref;

    public SettingsFragment() {

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

        sharedPref = getContext().getSharedPreferences("preferences", MODE_PRIVATE);
        String param = sharedPref.getString("user_dashboard_options", "default");

        if (param.equals("subs") || param.equals("default")) {
            binding.dataRadioGroup.check(R.id.subs_radio);
        } else if (param.equals("likes")) {
            binding.dataRadioGroup.check(R.id.likes_radio);
        } else if (param.equals("views")) {
            binding.dataRadioGroup.check(R.id.views_radio);
        } else {
            binding.dataRadioGroup.check(R.id.shares_radio);
        }


        binding.dataRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.subs_radio:
                    //print "subs"
                    sharedPref.edit().putString("user_dashboard_options", "subs").apply();
                    break;
                case R.id.likes_radio:
                    // do operations specific to this selection
                    sharedPref.edit().putString("user_dashboard_options", "likes").apply();
                    break;
                case R.id.views_radio:
                    // do operations specific to this selection
                    sharedPref.edit().putString("user_dashboard_options", "views").apply();
                    break;
                case R.id.shares_radio:
                    // do operations specific to this selection
                    sharedPref.edit().putString("user_dashboard_options", "shares").apply();
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