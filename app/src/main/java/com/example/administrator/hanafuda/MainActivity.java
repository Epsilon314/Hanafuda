package com.example.administrator.hanafuda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public static boolean isOffline = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button offline = findViewById(R.id.offline);
        Button online = findViewById(R.id.online);

        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOffline = true;
                Intent intent = new Intent(MainActivity.this,MainGameActivity.class);
                startActivity(intent);
            }
        });
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOffline = false;
                Intent intent = new Intent(MainActivity.this,NetworkActivity.class);
                startActivity(intent);
            }
        });

    }
}
