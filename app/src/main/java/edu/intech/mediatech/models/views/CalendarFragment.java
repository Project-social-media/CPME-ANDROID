package edu.intech.mediatech.models.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import edu.intech.mediatech.R;
import edu.intech.mediatech.adapters.PostAdapter;
import edu.intech.mediatech.databinding.FragmentCalendarBinding;
import edu.intech.mediatech.models.bdd.Post;
import edu.intech.mediatech.repositories.PostRepository;
import edu.intech.mediatech.viewmodels.PostViewModel;

public class CalendarFragment extends Fragment {

    FragmentCalendarBinding binding;
    PostViewModel postViewModel;

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
        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LiveData<List<Post>> posts = PostRepository.getInstance().getAllPosts();
        posts.observe(getViewLifecycleOwner(), posts1 -> {
            PostAdapter postAdapter = new PostAdapter(getContext(), posts1, post -> {
                postViewModel.setSelectedPost(post);
                Navigation.findNavController(requireActivity(), R.id.dashboardNavigationHost).navigate(R.id.action_calendarFragment_to_postDetailFragment);
            });
            binding.calendarList.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.calendarList.setAdapter(postAdapter);
        });
    }
}