package edu.intech.mediatech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import edu.intech.mediatech.databinding.ActivityDashboardBinding;
import edu.intech.mediatech.databinding.FragmentDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DrawerLayout drawerLayout = binding.drawerLayout;

        binding.imageMenu.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
    }


}