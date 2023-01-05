package edu.intech.mediatech.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.intech.mediatech.dao.UsersDao;
import edu.intech.mediatech.models.bdd.User;
import edu.intech.mediatech.models.bdd.UserRoomDatabase;
import edu.intech.mediatech.repositories.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private UsersDao mUsersDao;
    private LiveData<List<User>> mAllUsers;

    UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        mUsersDao = db.usersDao();
        mAllUsers = mUsersDao.getAllUsersByUsername();
    }

    LiveData<List<User>> getAllRoomUsers() {
        return mAllUsers;
    }

    void insert(User user) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            mUsersDao.insertUser(user);
        });
    }


    private static UserRepository instance;

    public static UserRepository getInstance(Application application) {
        if (instance == null) {
            instance = new UserRepository(application);
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

    public LiveData<User> getUserByUsername(String username) {
        final MutableLiveData<User> data = new MutableLiveData<>();
        Call<User> call = ApiService.getUserService().getUserByUsername(username);
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
}
