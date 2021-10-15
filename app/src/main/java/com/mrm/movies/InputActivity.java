package com.mrm.movies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {
    EditText enterN;
    Button go;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        enterN = findViewById(R.id.enterN);
        go = findViewById(R.id.goBtn);

        go.setOnClickListener(v -> {
            name = enterN.getText().toString();

            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("name", name);
            startActivity(i);
        });
    }
}