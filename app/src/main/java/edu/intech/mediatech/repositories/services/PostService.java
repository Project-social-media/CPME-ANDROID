package edu.intech.mediatech.repositories.services;

import java.util.List;

import edu.intech.mediatech.models.bdd.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    @GET("api/posts")
    Call<List<Post>> getNotPostedPosts();

    @GET("api/archives")
    Call<List<Post>> getArchivedPosts();
}
