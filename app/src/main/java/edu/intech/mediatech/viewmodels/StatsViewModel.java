package edu.intech.mediatech.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Map;

import edu.intech.mediatech.repositories.StatsRepository;

public class StatsViewModel extends AndroidViewModel {

    private StatsRepository statsRepository;

    public StatsViewModel(Application application) {
        super(application);
        this.statsRepository = new StatsRepository();
    }

    public LiveData<Map<String, Integer>> getTwitterUserPostStats(String token) {
        return statsRepository.getTwitterUserPostStats(token);
    }
}
