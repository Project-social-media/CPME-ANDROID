package edu.intech.mediatech.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.intech.mediatech.models.bdd.Post;
import edu.intech.mediatech.repositories.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {

    private static PostRepository instance;

    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }

    public LiveData<List<Post>> getAllPosts() {
        final MutableLiveData<List<Post>> data = new MutableLiveData<>();
        Call<List<Post>> call = ApiService.getPostService().getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d("request", "onResponse: " + response.toString());
                List<Post> posts = response.body();
                assert posts != null;
                data.setValue(posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + call.request().toString());
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
        return data;
    }
}
