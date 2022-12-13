package edu.intech.mediatech.models.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.mediatech.adapters.PostAdapter;
import edu.intech.mediatech.databinding.FragmentCalendarBinding;
import edu.intech.mediatech.models.DashboardActivity;
import edu.intech.mediatech.models.bdd.Post;
import edu.intech.mediatech.repositories.PostRepository;
import edu.intech.mediatech.repositories.UserRepository;

public class CalendarFragment extends Fragment {

    FragmentCalendarBinding binding;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LiveData<List<Post>> posts = PostRepository.getInstance().getAllPosts();
    }
}