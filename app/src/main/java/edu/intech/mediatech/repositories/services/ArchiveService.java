package edu.intech.mediatech.repositories.services;

import java.util.List;

import edu.intech.mediatech.models.bdd.Archive;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ArchiveService {
    @GET("api/archives")
    Call<List<Archive>> getAllArchives();
}
