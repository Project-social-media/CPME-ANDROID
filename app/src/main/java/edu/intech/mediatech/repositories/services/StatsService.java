package edu.intech.mediatech.repositories.services;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StatsService {
    @GET("api/stats/twitter/user")
    Call<Integer> getTwitterUserFollowers();

    @GET("api/stats/twitter/stats")
    Call<Map<String, Integer>> getTwitterPostStats();


}
