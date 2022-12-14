package edu.intech.mediatech.models.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import edu.intech.mediatech.adapters.PostAdapter;
import edu.intech.mediatech.databinding.FragmentCalendarBinding;
import edu.intech.mediatech.models.bdd.Post;
import edu.intech.mediatech.repositories.PostRepository;

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

        //I want to get the posts lists then i can display them in the recyclerview
        PostRepository postRepository = new PostRepository();
        LiveData<List<Post>> posts = postRepository.getAllPosts();
        posts.observe(getViewLifecycleOwner(), posts1 -> {
            PostAdapter postAdapter = new PostAdapter(getContext(), posts1);
            binding.calendarList.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.calendarList.setAdapter(postAdapter);
            Log.d("CalendarFragment", "onViewCreated: " + posts.getValue().size());
        });

    }
}