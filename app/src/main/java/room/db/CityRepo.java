package room.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class CityRepo {

    private CityDao mCityDao;
    private LiveData<List<City>> mAllCity;


    CityRepo(Application application) {
        CitiesDB db = CitiesDB.getDatabase(application);
        mCityDao = db.CityDao();
        mAllCity = mCityDao.getAlphabetizedCities();
    }

    LiveData<List<City>> getAllCities() {
        return mAllCity;
    }

    void insert(City city) {
        CitiesDB.databaseWriteExecutor.execute(() -> {
            mCityDao.insertAll(city);
        });
    }
}