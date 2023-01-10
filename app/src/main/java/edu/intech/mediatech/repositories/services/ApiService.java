package edu.intech.mediatech.repositories.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    public static final String BASE_URL = "https://api.nauwk.fr";

    private static Retrofit retrofit = null;
    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    //static getter for UserService
    public static UserService getUserService() {
        return getRetrofitInstance().create(UserService.class);
    }

    public static PostService getPostService() {
        return getRetrofitInstance().create(PostService.class);
    }

    public static ArchiveService getArchiveService() {
        return getRetrofitInstance().create(ArchiveService.class);
    }

    public static StatsService getStatsService() {
        return getRetrofitInstance().create(StatsService.class);
    }
}
