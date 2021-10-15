package com.mrm.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InfoActivity extends AppCompatActivity {
    TextView filmName , filmYear , filmDirector;
    ImageView posterFilm;
    String name , year , director , poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        filmName = findViewById(R.id.filmNameInInfo);
        filmYear = findViewById(R.id.filmYearInInfo);
        filmDirector = findViewById(R.id.filmDirectorInInfo);

        posterFilm = findViewById(R.id.posterFilmInInfo);

        name = getIntent().getStringExtra("name");
        year = getIntent().getStringExtra("year");
        director = getIntent().getStringExtra("director");
        poster = getIntent().getStringExtra("poster");

        filmName.setText(name);
        filmYear.setText(year);
        filmDirector.setText(director);

        posterFilm.setImageBitmap(getImage(poster));
    }

    private Bitmap getImage(String src) {
        try {

            URL url = new URL(src);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setDoInput(true);
            huc.connect();
            InputStream i = huc.getInputStream();
            return BitmapFactory.decodeStream(i);

        } catch (IOException e) {
            return null;
        }
    }
}