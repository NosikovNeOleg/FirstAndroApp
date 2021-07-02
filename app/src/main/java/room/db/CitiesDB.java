package room.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class CitiesDB extends RoomDatabase {

    public abstract CityDao CityDao();

    private static volatile CitiesDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CitiesDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CitiesDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CitiesDB.class, "CitiesDataBase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
