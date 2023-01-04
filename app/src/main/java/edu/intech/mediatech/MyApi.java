package edu.intech.mediatech;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MyApi {
    @GET("users")
    Call<List<User>> getUsers();
}