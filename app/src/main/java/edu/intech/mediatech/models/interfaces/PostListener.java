package edu.intech.mediatech.models.interfaces;

import edu.intech.mediatech.models.bdd.Post;

public interface PostListener {
    void onPostClicked(Post post);
}
