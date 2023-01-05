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

    @GET("api/users/email/{email}")
    Call<User> getUserByMail(@Path("email") String email);

    @POST("api/auth/login")
    Call<User> authenticate(@Body User user);

    @PUT("api/users/update/{email}")
    Call<User> updateUser(@Path("email") String email, @Body User user);

}