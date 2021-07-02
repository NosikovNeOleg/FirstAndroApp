package room.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CityDao {

    @Insert
    void insertAll(City city);

    @Delete
    void delete(City city);

    @Query("SELECT * FROM City")
    List<City> getAllCities();

    @Query("SELECT * FROM City ORDER BY 'City' ASC")
    LiveData<List<City>> getAlphabetizedCities();

}
