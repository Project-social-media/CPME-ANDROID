package edu.intech.mediatech.models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;

import edu.intech.mediatech.models.fragments.CalendarFragment;
import edu.intech.mediatech.R;
import edu.intech.mediatech.databinding.ActivityDashboardBinding;
import edu.intech.mediatech.models.fragments.DashboardFragment;
import edu.intech.mediatech.models.fragments.SettingsFragment;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DrawerLayout drawerLayout = binding.drawerLayout;

        binding.imageMenu.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        binding.navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.back_dashboard:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.accueil:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new DashboardFragment()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.calendrier:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new CalendarFragment()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.parametres:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new SettingsFragment()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.deconnexion:
                    //disconnect user then new intent to MaiNAcitivity
                    finish();
                    break;
            }
            return true;
        });
    }


}