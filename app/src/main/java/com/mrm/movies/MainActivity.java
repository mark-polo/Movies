package com.mrm.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrm.movies.API.Controller;
import com.mrm.movies.API.MovieApi;
import com.mrm.movies.Adapter.MovieAdapter;
import com.mrm.movies.Model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button addBtn;
    RecyclerView rv;
    List<Model> models;
    MovieAdapter adapter;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieApi api = Controller.getApi();
        addBtn = findViewById(R.id.addBtn);
        models = new ArrayList<>();
        rv = findViewById(R.id.rv_movie);

        rv.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL, false));

        adapter = new MovieAdapter(models, this);

        rv.setAdapter(adapter);

        addBtn.setOnClickListener(v -> {
            Intent i = new Intent(this, InputActivity.class);
            startActivity(i);
        });

        name = getIntent().getStringExtra("name");

        api.getMovies(name).enqueue(new Callback<Model>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
                assert response.body() != null;
                models.add(response.body());
                Objects.requireNonNull(rv.getAdapter()).notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "FUUUUUUUCK!!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}