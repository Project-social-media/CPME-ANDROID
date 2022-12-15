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
import edu.intech.mediatech.models.bdd.Post;
import edu.intech.mediatech.models.interfaces.PostListener;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final Context ctx;
    private final List<Post> posts;
    private final PostListener listener;

    public PostAdapter(Context ctx, List<Post> posts, PostListener listener) {
        this.ctx = ctx;
        this.posts = posts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.post_calendar_layout, parent, false);
        return new PostViewHolder(view);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.message.setText(String.valueOf(posts.get(position).getMessage()).substring(0, 7).concat("..."));

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            dateFormat = new SimpleDateFormat("dd/MM/yy");
        }
        assert dateFormat != null;
        String formattedDate = dateFormat.format(posts.get(position).getDate());

        holder.date.setText(formattedDate);

        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);

        if (posts.get(position).getDate().before(currentDate)) {
            holder.statusIcon.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.finished));
        } else {
            holder.statusIcon.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.timed));
        }

        holder.constraintLayout.setOnClickListener(v -> {
            listener.onPostClicked(posts.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    protected static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        ImageView statusIcon;
        TextView date;
        ConstraintLayout constraintLayout;

        private PostViewHolder(View itemView) {
            super(itemView);
            this.message = itemView.findViewById(R.id.post_message);
            this.statusIcon = itemView.findViewById(R.id.status_icon);
            this.date = itemView.findViewById(R.id.date_text);
            this.constraintLayout = itemView.findViewById(R.id.post_main_layout);
        }
    }
}
