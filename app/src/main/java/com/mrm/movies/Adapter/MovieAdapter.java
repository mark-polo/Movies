package com.mrm.movies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrm.movies.InfoActivity;
import com.mrm.movies.Model.Model;
import com.mrm.movies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private final List<Model> movies;
    private final Context context;

    public MovieAdapter(List<Model> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieAdapter.MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Intent i = new Intent(context, InfoActivity.class);

        Picasso.with(context).load(movies.get(position).getPoster()).into(holder.poster);
        holder.name.setText(movies.get(position).getTitle());
        holder.year.setText(movies.get(position).getYear());
        holder.desc.setText(movies.get(position).getPlot());

        holder.itemView.setOnClickListener(v -> {
            i.putExtra("name", movies.get(position).getTitle());
            i.putExtra("year", movies.get(position).getYear());
            i.putExtra("director", movies.get(position).getDirector());
            i.putExtra("poster", movies.get(position).getPoster());

            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView name , year , desc;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.poster);
            name = itemView.findViewById(R.id.filmName);
            year = itemView.findViewById(R.id.filmYear);
            desc = itemView.findViewById(R.id.filmDescription);
        }
    }
}