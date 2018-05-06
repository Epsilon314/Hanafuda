package com.example.administrator.hanafuda;

import android.support.v7.app.AlertDialog;
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
        Button help = findViewById(R.id.help);
        Button about = findViewById(R.id.about);

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

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHelpDiag();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void showHelpDiag() {
        final AlertDialog.Builder help = new AlertDialog.Builder(this);
        help.show();
    }
}
