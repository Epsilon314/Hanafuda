package com.example.administrator.hanafuda;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class NetworkActivity extends AppCompatActivity {

    public static boolean connected = false;
    public static String IP;
    public static String inputIP;
    public static Client clientThread;
    public static Server serverThread;
    public static boolean isServer = false;
    public static boolean isClient = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        TextView tv=findViewById(R.id.showYourIpAddress);
        tv.setText(IP=getIpAddress());
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
        serverThread = new Server ();
        serverThread.start();
        Toast.makeText(NetworkActivity.this, "等待连接", Toast.LENGTH_SHORT).show();

        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(connected){
            Toast.makeText(NetworkActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
            isServer = true;
            Intent intent = new Intent(NetworkActivity.this,MainGameActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(NetworkActivity.this, "等待超时,请重新尝试建立服务器端", Toast.LENGTH_SHORT).show();
            serverThread.interrupt();
        }
    }

    private void toBeAClient(){
        EditText input=findViewById(R.id.ipInput);
        inputIP = input.getText().toString();
        clientThread = new Client ();
        clientThread.start();
        Toast.makeText(NetworkActivity.this, "等待连接", Toast.LENGTH_SHORT).show();

        try {
            Thread.currentThread().sleep(5000);//阻断5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(connected){
            //if succeed
            Toast.makeText(NetworkActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
            isClient = true;
            Intent intent = new Intent(NetworkActivity.this,MainGameActivity.class);
            startActivity(intent);
        }
        else {
            AlertDialog.Builder connenctFailDialog=new AlertDialog.Builder(NetworkActivity.this);
            connenctFailDialog.setMessage("Connect fail!");
            connenctFailDialog.setPositiveButton("try again",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            connenctFailDialog.show();
            clientThread.interrupt();
        }
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
