package edu.intech.mediatech.repositories.services;

import java.util.List;

import edu.intech.mediatech.models.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @GET("api/users/all")
    Call<List<User>> getUsers();

    //POST request to authenticate to the api

    @POST("api/auth/login")
    Call<User> authenticate(@Body User user);

}