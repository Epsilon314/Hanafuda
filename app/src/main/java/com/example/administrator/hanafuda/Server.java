package com.example.administrator.hanafuda;

/**
 * Created by BOOK on 2018/5/5.
 */

import android.support.annotation.Nullable;

import java.net.*;
import java.io.*;

public class Server extends Thread {
    public ServerSocket mServer;
    public Socket mSocket = null;
    private boolean flag = true;
    private static BufferedInputStream inputStream;
    private static BufferedOutputStream outputStream;
    @Override
    public void run(){

        try {
            mServer = new ServerSocket(8888);
        } catch (Exception e) {flag = false;System.out.println("ServerSocket Error!");}

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                if (!flag)
                    continue;
                mSocket = mServer.accept();
                if (mSocket == null)
                    continue;
                byte[] buff = new byte[256];
                int len = 0;
                outputStream = new BufferedOutputStream(mSocket.getOutputStream());
                inputStream = new BufferedInputStream(mSocket.getInputStream());
                /*Connecting*/
                if (!NetworkActivity.connected){
                    while (!Thread.currentThread().isInterrupted()&&(len=inputStream.read(buff)) != -1) {
                        if (!(len>0)){
                            continue;
                        }
                        NetworkActivity.connected = true;
                        outputStream.write(1);
                        outputStream.flush();
                        break;
                    }
                }
                while (!Thread.currentThread().isInterrupted()){

                }
                outputStream.close();
                inputStream.close();
            }
            catch (InterruptedException e) {}
            catch (IOException e) {}
        }
    }

    public static synchronized boolean dataWrite(byte [] send){
        if(!Thread.currentThread().isInterrupted()){
            try {
                outputStream.write(send);
                outputStream.flush();
                return true;
            }
            catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        else{
            System.out.println("Connection is interrupted.");
            return false;
        }
    }

    @Nullable
    public static synchronized byte [] dataRead(){
        if(!Thread.currentThread().isInterrupted()){
            try {
                byte [] get =new byte [512];
                int len =0;
                len=inputStream.read(get);
                System.out.println("bbbbbbbbbbbbbb");
                return get;
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        else{
            System.out.println("Connection is interrupted.");
            return null;
        }
    }
}