package edu.intech.mediatech.repositories.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    public static final String BASE_URL = "http://10.0.2.2:3000/";

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
}
