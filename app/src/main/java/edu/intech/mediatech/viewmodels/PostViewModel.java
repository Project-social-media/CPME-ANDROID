package edu.intech.mediatech.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.intech.mediatech.models.bdd.Post;

public class PostViewModel extends ViewModel {

    private MutableLiveData<Post> selectedPost = new MutableLiveData<>();

    public LiveData<Post> getSelectedPost() {
        return selectedPost;
    }

    public void setSelectedPost(Post post) {
        selectedPost.setValue(post);
    }
}
