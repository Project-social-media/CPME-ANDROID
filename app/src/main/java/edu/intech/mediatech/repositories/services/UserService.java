package edu.intech.mediatech.repositories.services;

import java.util.List;

import edu.intech.mediatech.models.bdd.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("api/users/all")
    Call<List<User>> getUsers();

    @POST("api/auth/login")
    Call<User> authenticate(@Body User user);

    @PUT("api/users/update/{username}")
    Call<User> updateUser(@Path("username") String username, @Body User user);

}