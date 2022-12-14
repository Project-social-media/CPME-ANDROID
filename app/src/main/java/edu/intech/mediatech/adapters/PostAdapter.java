package edu.intech.mediatech.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.intech.mediatech.R;
import edu.intech.mediatech.models.bdd.Post;
import edu.intech.mediatech.models.interfaces.RvItemClickListener;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final Context ctx;
    private final List<Post> posts;

    RvItemClickListener listener;

    public PostAdapter(Context ctx, List<Post> posts) {
        this.ctx = ctx;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.post_calendar_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.id.setText(String.valueOf(posts.get(position).getMessage().substring(0, 10).concat("...")));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    protected static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        ConstraintLayout constraintLayout;

        private PostViewHolder(View itemView) {
            super(itemView);
            this.id = itemView.findViewById(R.id.post_id);
            this.constraintLayout = itemView.findViewById(R.id.post_main_layout);

        }
    }

    public void setListener(RvItemClickListener listener) {
        this.listener = listener;
    }
}
