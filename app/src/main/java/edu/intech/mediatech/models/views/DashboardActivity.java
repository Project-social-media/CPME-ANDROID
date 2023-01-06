package edu.intech.mediatech.models.views;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import edu.intech.mediatech.R;
import edu.intech.mediatech.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;
    SharedPreferences sharedPref;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.dashboardNavigationHost) != null ? (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.dashboardNavigationHost) : null;
        NavController navController = navHostFragment != null ? navHostFragment.getNavController() : null;

        assert navController != null;
        NavigationUI.setupWithNavController(binding.navView, navController);

        final DrawerLayout drawerLayout = binding.drawerLayout;

        binding.imageMenu.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });


        binding.navView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.deconnexion) {
                sharedPref = getSharedPreferences("preferences", MODE_PRIVATE);
                sharedPref.edit().putString("user_token", "").apply();
                sharedPref.edit().putString("user_username", "").apply();
                sharedPref.edit().putString("user_password", "").apply();
                finish();
            }

            NavigationUI.onNavDestinationSelected(item, navController);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;

        });
    }
}