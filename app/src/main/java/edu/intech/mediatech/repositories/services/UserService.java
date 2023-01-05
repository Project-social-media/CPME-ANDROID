package edu.intech.mediatech.repositories.services;

import java.util.List;

import edu.intech.mediatech.models.bdd.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @GET("api/users/all")
    Call<List<User>> getUsers();

    @GET("api/users/username/{username}")
    Call<User> getUserByUsername(String username);

    @POST("api/auth/login")
    Call<User> authenticate(@Body User user);

}