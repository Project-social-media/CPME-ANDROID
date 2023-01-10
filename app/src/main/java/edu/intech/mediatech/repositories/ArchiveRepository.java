package edu.intech.mediatech.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.intech.mediatech.models.bdd.Archive;
import edu.intech.mediatech.models.bdd.Post;
import edu.intech.mediatech.repositories.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArchiveRepository {

    private PostRepository instance;

    public PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }

    public LiveData<List<Archive>> getAllArchives() {
        final MutableLiveData<List<Archive>> data = new MutableLiveData<>();
        ApiService.getArchiveService().getAllArchives()
                .enqueue(new Callback<List<Archive>>() {
                    @Override
                    public void onResponse(Call<List<Archive>> call, Response<List<Archive>> response) {
                        if (response.isSuccessful()) {
                            data.setValue(response.body());
                        } else {
                            Log.e("TAG", "Error code: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Archive>> call, Throwable t) {
                        Log.e("TAG", "Error: " + t.getMessage());
                    }
                });
        return data;
    }
}
