package edu.intech.mediatech.models.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import edu.intech.mediatech.databinding.FragmentDashboardBinding;


public class DashboardFragment extends Fragment {

    FragmentDashboardBinding binding;
    public static String dashboardDataType = "subs";

    Context context = getContext();


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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.facebookBtn.setOnClickListener(v -> {

            // open the facebook app using facebook url
            String facebookUrl = "https://www.facebook.com/profile.php?id=100086109346988";
            String facebookUrlScheme = "fb://facewebmodal/f?href=" + facebookUrl;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrlScheme));
            intent.setPackage("com.facebook.katana");
            startActivity(intent);
        });

        binding.instagramBtn.setOnClickListener(v -> {

            // open the instagram app using instagram url
            String instagramUrl = "https://instagram.com/_u/mediatech_aen";

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));
            intent.setPackage("com.instagram.android");
            startActivity(intent);
        });

        binding.twitterBtn.setOnClickListener(v -> {

            // open the twitter app using twitter url
            String twitterUrl = "https://twitter.com/MediaTechAEN";

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
            startActivity(intent);
        });

        binding.linkedinBtn.setOnClickListener(v -> {

            // open the linkedin app using linkedin url
            String linkedinUrl = "https://www.linkedin.com/in/mediatech-aen-97aaa2251/";

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl));
            startActivity(intent);
        });


    }

}