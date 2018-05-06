package com.example.administrator.hanafuda;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class NetworkActivity extends AppCompatActivity {

    public static String yourIP;
    public static String inputIP;

    public static boolean isServer = false;
    public static boolean isClient = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);


        TextView tv=findViewById(R.id.showYourIpAddress);
        tv.setText(yourIP=getIpAddress());
        tv.setGravity(Gravity.CENTER);

        Button client = findViewById(R.id.clientButton);
        Button server = findViewById(R.id.serverButton);

        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toBeAServer();
            }
        });
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toBeAClient();
            }
        });

    }

    private void toBeAServer(){

        isServer = true;
        Intent intent = new Intent(NetworkActivity.this,MainGameActivity.class);
        startActivity(intent);

    }

    private void toBeAClient(){

        EditText input=findViewById(R.id.ipInput);
        inputIP = input.getText().toString();
        isClient = true;
        Intent intent = new Intent(NetworkActivity.this,MainGameActivity.class);
        startActivity(intent);

    }

    /*Get IP*/
    private String getIpAddress(){
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        System.out.println("This is what wifimanager gets");
        System.out.println(ipAddress);
        String ipAddressFormatted = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff),
                (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
        return ipAddressFormatted;
    }
}
