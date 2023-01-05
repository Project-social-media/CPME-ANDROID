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

    public LiveData<List<User>> getAllUsers() {
        final MutableLiveData<List<User>> data = new MutableLiveData<>();
        Call<List<User>> call = ApiService.getUserService().getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("request", "onResponse: " + response.toString());
                List<User> users = response.body();
                assert users != null;
                data.setValue(users);

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + call.request().toString());
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
        return data;
    }

    //implement post method authenticate and return result

    public LiveData<User> authenticateUser(User user) {
        final MutableLiveData<User> data = new MutableLiveData<>();
        Call<User> call = ApiService.getUserService().authenticate(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("request", "onResponse: " + response.toString());
                User user = response.body();
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

    //implement put method update
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
