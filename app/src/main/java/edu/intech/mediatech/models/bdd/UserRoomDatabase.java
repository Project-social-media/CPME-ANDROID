package edu.intech.mediatech.models.bdd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.intech.mediatech.dao.UsersDao;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

    public abstract UsersDao usersDao();

    private static volatile UserRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UserRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    UserRoomDatabase.class, "test")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}