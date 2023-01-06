package edu.intech.mediatech.repositories.services;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface StatsService {
    @GET("api/stats/twitter/stats")
    Call<Map<String, Integer>> getTwitterPostStats(@Header("Authorization") String token);


}
