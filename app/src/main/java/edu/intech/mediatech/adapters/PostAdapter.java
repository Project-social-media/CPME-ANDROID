package edu.intech.mediatech.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.intech.mediatech.R;
import edu.intech.mediatech.databinding.PostCalendarLayoutBinding;
import edu.intech.mediatech.models.bdd.Post;
import edu.intech.mediatech.models.interfaces.PostListener;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final Context ctx;
    private final List<Post> posts;
    private final PostListener listener;
    PostCalendarLayoutBinding binding;

    public PostAdapter(Context ctx, List<Post> posts, PostListener listener) {
        this.ctx = ctx;
        this.posts = posts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PostCalendarLayoutBinding.inflate(LayoutInflater.from(ctx), parent, false);
        return new PostViewHolder(binding);
    }

    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.binding.postMessage.setText(post.getMessage() + "...");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        String formattedDate = dateFormat.format(post.getDate());
        holder.binding.dateText.setText("Prévu pour le " + formattedDate + " sur");
        holder.binding.statusColor.setText("Statut : non posté");

        if (post.isFacebook()) {
            holder.binding.imageNetwork.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.facebook_logo));
        } else if (post.isTwitter()) {
            holder.binding.imageNetwork.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.twitter_logo));
        } else if (post.isLinkedin()) {
            holder.binding.imageNetwork.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.linkedin_logo));
        } else if (post.isInstagram()) {
            holder.binding.imageNetwork.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.instagram_logo));
        }

        holder.binding.postMainLayout.setOnClickListener(v -> listener.onPostClicked(post));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    protected static class PostViewHolder extends RecyclerView.ViewHolder {
        PostCalendarLayoutBinding binding;

        private PostViewHolder(PostCalendarLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
