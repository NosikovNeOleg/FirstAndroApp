package room.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CityViewModel extends AndroidViewModel {

    private CityRepo mRepository;

    private final LiveData<List<City>> mAllCities;

    public CityViewModel (Application application) {
        super(application);
        mRepository = new CityRepo(application);
        mAllCities = mRepository.getAllCities();
    }

    LiveData<List<City>> getAllCities() { return mAllCities; }

    public void insert(City city) { mRepository.insert(city); }
}