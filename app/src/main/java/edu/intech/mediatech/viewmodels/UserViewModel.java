package edu.intech.mediatech.viewmodels;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.intech.mediatech.models.bdd.User;
import edu.intech.mediatech.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private final UserRepository userRepository;

    public UserViewModel(Application application) {
        super(application);
        this.userRepository = UserRepository.getInstance();
    }

    public LiveData<User> authenticateUser(User user) {
        return userRepository.authenticateUser(user);
    }

    public void updateUser(String username, User user) {
        userRepository.updateUser(username, user);
    }
}
