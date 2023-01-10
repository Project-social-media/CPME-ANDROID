package edu.intech.mediatech.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.intech.mediatech.models.bdd.Post;
import edu.intech.mediatech.repositories.PostRepository;

public class PostViewModel extends AndroidViewModel {

    private MutableLiveData<Post> selectedPost = new MutableLiveData<>();
    private PostRepository postRepository;

    public PostViewModel(@NonNull Application application) {
        super(application);
        this.postRepository = new PostRepository();
    }

    public LiveData<List<Post>> getAllPosts(){
        return postRepository.getAllPosts();
    }

    public LiveData<Post> getSelectedPost() {
        return selectedPost;
    }

    public void setSelectedPost(Post post) {
        selectedPost.setValue(post);
    }
}
