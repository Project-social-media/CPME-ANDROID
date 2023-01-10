package edu.intech.mediatech.models.views;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import edu.intech.mediatech.R;
import edu.intech.mediatech.databinding.FragmentDashboardBinding;
import edu.intech.mediatech.models.bdd.Archive;
import edu.intech.mediatech.models.bdd.Post;
import edu.intech.mediatech.viewmodels.ArchiveViewModel;
import edu.intech.mediatech.viewmodels.PostViewModel;
import edu.intech.mediatech.viewmodels.StatsViewModel;
import edu.intech.mediatech.viewmodels.UserViewModel;


public class DashboardFragment extends Fragment {

    FragmentDashboardBinding binding;
    SharedPreferences sharedPref;
    StatsViewModel statsViewModel;
    PostViewModel postViewModel;
    ArchiveViewModel archiveViewModel;

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
        this.postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        this.statsViewModel = new ViewModelProvider(this).get(StatsViewModel.class);
        this.archiveViewModel = new ViewModelProvider(this).get(ArchiveViewModel.class);
        return binding.getRoot();
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPref = getContext().getSharedPreferences("preferences", MODE_PRIVATE);
        String token = sharedPref.getString("user_token", null);

        LiveData<List<Post>> posts = postViewModel.getAllPosts();
        LiveData<List<Archive>> archives = archiveViewModel.getAllArchives();

        posts.observe(getViewLifecycleOwner(), posts1 -> {
            if (posts1 != null && !posts1.isEmpty()) {
                Post firstPost = posts1.get(0);
                Log.d("POST", firstPost.isFacebook() + "");
            }
        });

        archives.observe(getViewLifecycleOwner(), archives1 -> {
            if (archives1 != null && !archives1.isEmpty()) {
                Archive lastPost = archives1.get(archives1.size() - 1);
                Log.d("ARCHIVE", lastPost.getMessage() + "");
            }
        });

        if (sharedPref.getString("user_dashboard_network", "twitter").equals("twitter")) {
            binding.dp1Network.setText("(Twitter)");
            binding.imageView.setImageDrawable(getResources().getDrawable(R.drawable.twitter_logo));
            statsViewModel.getTwitterUserPostStats(token).observe(getViewLifecycleOwner(), stats -> {
                binding.subsTb.setText("Nombre de followers:" + " " + stats.get("followersCount"));
                binding.likesTb.setText("Nombre de likes:" + " " + stats.get("likesCount") + " " + "(90j)");
                binding.sharesTb.setText("Nombre de retweets:" + " " + stats.get("sharesCount") + " " + "(90j)");
                binding.viewTb.setText("Nombre de vues:" + " " + stats.get("viewsCount") + " " + "(90j)");
            });
        } else {
            Toast.makeText(getContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
        }
    }
}