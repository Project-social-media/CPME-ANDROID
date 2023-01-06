package edu.intech.mediatech.repositories;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.core.app.AppLaunchChecker;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.intech.mediatech.repositories.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;

import java.util.Map;

public class StatsRepository {

    private static StatsRepository instance;
    SharedPreferences sharedPref;

    public static StatsRepository getInstance() {
        if (instance == null) {
            instance = new StatsRepository();
        }
        return instance;
    }

    public LiveData<Map<String, Integer>> getTwitterUserPostStats(String token) {
        final MutableLiveData<Map<String, Integer>> data = new MutableLiveData<>();
        // Création du callback global
        Callback<Map<String, Integer>> callback = new Callback<Map<String, Integer>>() {
            @Override
            public void onResponse(Call<Map<String, Integer>> call, Response<Map<String, Integer>> response) {
                Log.d("request", "onResponse: " + response.toString());
                Map<String, Integer> stats = response.body();
                assert stats != null;
                data.setValue(stats);
            }

            @Override
            public void onFailure(Call<Map<String, Integer>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + call.request());
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        };

        // Utilisation du callback global pour l'appel à l'API
        Call<Map<String, Integer>> call = ApiService.getStatsService().getTwitterPostStats(token);
        call.enqueue(callback);
        return data;
    }


}
