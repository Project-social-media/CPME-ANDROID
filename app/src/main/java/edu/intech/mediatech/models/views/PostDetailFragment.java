package edu.intech.mediatech.models.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.intech.mediatech.databinding.FragmentPostDetailBinding;
import edu.intech.mediatech.viewmodels.PostViewModel;

public class PostDetailFragment extends Fragment {

    FragmentPostDetailBinding binding;
    PostViewModel postViewModel;

    public PostDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);
        postViewModel.getSelectedPost().observe(getViewLifecycleOwner(), post -> {
            //binding.postTitle.setText(post.get());
            //binding.postContent.setText(post.getContent());
        });

    }
}