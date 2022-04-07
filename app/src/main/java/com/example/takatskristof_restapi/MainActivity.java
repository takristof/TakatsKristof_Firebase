package com.example.takatskristof_restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnFelvetelView,btnListaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnFelvetelView.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(i);
            finish();
        });
        btnListaView.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, ListResultActivity.class);
            startActivity(i);
            finish();
        });
    }

    private void init(){
        btnFelvetelView=findViewById(R.id.btnFelvetelView);
        btnListaView=findViewById(R.id.btnListaView);
    }
}