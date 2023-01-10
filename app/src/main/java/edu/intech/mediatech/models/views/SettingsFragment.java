package edu.intech.mediatech.models.views;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
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
        String param = sharedPref.getString("user_dashboard_network", "default");

        if (param.equals("twitter") || param.equals("default")) {
            binding.dataRadioGroup.check(R.id.twt_radio);
        } else if (param.equals("facebook")) {
            binding.dataRadioGroup.check(R.id.fb_radio);
        } else if (param.equals("linkedin")) {
            binding.dataRadioGroup.check(R.id.lkd_radio);
        } else {
            binding.dataRadioGroup.check(R.id.insta_radio);
        }


        binding.dataRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.twt_radio:
                    //print "subs"
                    sharedPref.edit().putString("user_dashboard_network", "twitter").apply();
                    break;
                case R.id.fb_radio:
                    // do operations specific to this selection
                    sharedPref.edit().putString("user_dashboard_network", "facebook").apply();
                    break;
                case R.id.lkd_radio:
                    // do operations specific to this selection
                    sharedPref.edit().putString("user_dashboard_network", "linkedin").apply();
                    break;
                case R.id.insta_radio:
                    // do operations specific to this selection
                    sharedPref.edit().putString("user_dashboard_network", "instagram").apply();
                    break;
            }
        });
    }
}