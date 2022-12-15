package edu.intech.mediatech.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.intech.mediatech.models.bdd.User;

@Dao
public interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);

    @Query("DELETE FROM users")
    void deleteAll();

    @Query("SELECT * FROM users ORDER BY username ASC")
    LiveData<List<User>> getAllUsersByUsername();

}
