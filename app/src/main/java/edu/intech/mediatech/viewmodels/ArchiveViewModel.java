package edu.intech.mediatech.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.mediatech.models.bdd.Archive;
import edu.intech.mediatech.repositories.ArchiveRepository;

public class ArchiveViewModel extends AndroidViewModel {

    private ArchiveRepository archiveRepository;

    public ArchiveViewModel(Application application) {
        super(application);
        this.archiveRepository = new ArchiveRepository();
    }

    public LiveData<List<Archive>> getAllArchives(){
        return archiveRepository.getAllArchives();
    }
}
