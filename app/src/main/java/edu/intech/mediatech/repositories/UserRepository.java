package edu.intech.mediatech.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.intech.mediatech.models.bdd.User;
import edu.intech.mediatech.repositories.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private LiveData<List<User>> mAllUsers;

    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public LiveData<User> getUserByToken(String token) {
        final MutableLiveData<User> data = new MutableLiveData<>();
        Call<User> call = ApiService.getUserService().getUserByToken("Bearer " + token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("request", "onResponse: " + response.toString());
                User user = response.body();
                assert user != null;
                data.setValue(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", "onFailure: " + call.request().toString());
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
        return data;
    }



    public LiveData<User> getUserByEmail(String email) {
        final MutableLiveData<User> data = new MutableLiveData<>();
        Call<User> call = ApiService.getUserService().getUserByMail(email);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User user = response.body();
                    data.setValue(user);
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", "onFailure: " + call.request().toString());
                Log.d("TAG", "onFailure: " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }


    public LiveData<String> authenticateUser(User user) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        Call<User> call = ApiService.getUserService().authenticate(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String token = response.headers().get("Authorization");
                data.setValue(token);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", "onFailure: " + call.request().toString());
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
        return data;
    }

    public void updateUser(String username, User user) {
        Call<User> call = ApiService.getUserService().updateUser(username, user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("request", "onResponse: " + response.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", "onFailure: " + call.request().toString());
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }
}
