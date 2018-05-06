package com.example.administrator.hanafuda;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class NetworkInterface {
    private ServerSocket mServer;
    private Socket mSocket = null;
    private String ip = NetworkActivity.inputIP;
    private boolean flag = true;
    private static BufferedInputStream inputStream;
    private static BufferedOutputStream outputStream;
    private static boolean needToRead = false;
    private static boolean needToSend = false;
    private static boolean sendIsCompleted = false;
    private static boolean readIsCompleted = false;
    private byte [] buffer;
    private int size;
    private Server serverThread;
    private Client clientThread;

    NetworkInterface (boolean isServer, boolean isClient){

        if(isServer && !isClient){
            serverThread = new Server();
            serverThread.start();
        }
        if(isClient && !isServer){
            clientThread = new Client ();
            clientThread.start();
        }
    }

    private class Client extends Thread{

        @Override
        public void run() {
            super.run();

            while (!Thread.currentThread().isInterrupted()) {
                try{
                    mSocket = new Socket();
                    mSocket.connect(new InetSocketAddress(ip, 8888), 10000); // hard-code server address
                }catch(Exception e){flag = false;System.out.println("Connect fail!");}

                try {
                    Thread.sleep(1000);
                    if (!flag){
                        continue;
                    }
                    byte[] buff = new byte[4];
                    int len = 0;
                    String msg;
                    inputStream = new BufferedInputStream(mSocket.getInputStream());
                    outputStream = new BufferedOutputStream(mSocket.getOutputStream());
                    outputStream.write(1);
                    outputStream.flush();
                /*Connecting*/
                    while (!Thread.currentThread().isInterrupted()&&(len = inputStream.read(buff)) != -1){
                        if (!(len>0)){
                            continue;
                        }
                        MainGameActivity.connected = true;
                        break;
                    }
                    while (mSocket.isConnected()){
                        if (needToSend){
                            outputStream.write(buffer);
                            outputStream.flush();
                            needToSend = false;
                            sendIsCompleted = true;
                        }
                        if (needToRead){
                            buffer = new byte [512];
                            size = inputStream.read(buffer);
                            needToRead = false;
                            readIsCompleted = true;
                        }
                    }
                    outputStream.close();
                    inputStream.close();
                }
                catch (InterruptedException e) {}
                catch (IOException e) {}
            }

        }
        public synchronized boolean dataWrite(byte [] send){
            if(mSocket.isConnected()){
                sendIsCompleted = false;
                buffer = new byte [512];
                buffer = send;
                needToSend = true;
                while(!sendIsCompleted){}
                return true;
            }
            else {
                System.out.println("Connection is closed.");
                return false;
            }
        }

        public synchronized int dataRead(byte [] read){
            if(mSocket.isConnected()){
                readIsCompleted = false;
                needToRead = true;
                while(!readIsCompleted){}
                int len = size;
                for (int i = 0;i < len;++i){
                    read[i]=buffer[i];
                }
                return len;
            }
            else{
                System.out.println("Connection is closed.");
                return -1;
            }
        }

    }

    private class Server extends Thread {

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
                    if (!MainGameActivity.connected){
                        while (!Thread.currentThread().isInterrupted()&&(len=inputStream.read(buff)) != -1) {
                            if (!(len>0)){
                                continue;
                            }
                            MainGameActivity.connected = true;
                            outputStream.write(1);
                            outputStream.flush();
                            break;
                        }
                    }
                    while (mSocket.isConnected()){
                        if (needToSend){
                            outputStream.write(buffer);
                            outputStream.flush();
                            needToSend = false;
                            sendIsCompleted = true;
                        }
                        if (needToRead){
                            buffer = new byte [512];
                            size = inputStream.read(buffer);
                            needToRead = false;
                            readIsCompleted = true;
                        }
                    }
                    outputStream.close();
                    inputStream.close();
                    flag = false;
                }
                catch (InterruptedException e) {}
                catch (IOException e) {}
            }
        }
        public synchronized boolean dataWrite(byte [] send){
            if(mSocket.isConnected()){
                sendIsCompleted = false;
                buffer = new byte [512];
                buffer = send;
                needToSend = true;
                while(!sendIsCompleted){}
                return true;
            }
            else {
                System.out.println("Connection is closed.");
                return false;
            }
        }
        public synchronized int dataRead(byte [] read){
            if(mSocket.isConnected()){
                readIsCompleted = false;
                needToRead = true;
                while(!readIsCompleted){}
                int len = size;
                for (int i = 0;i < len;++i){
                    read[i]=buffer[i];
                }
                return len;
            }
            else{
                System.out.println("Connection is closed.");
                return -1;
            }
        }
    }

    public synchronized boolean dataWrite(byte [] send){
        if(mSocket.isConnected() && NetworkActivity.isServer){
            serverThread.dataWrite(send);
            return true;
        }
        if(mSocket.isConnected() && NetworkActivity.isClient){
            clientThread.dataWrite(send);
            return true;
        }
        return false;
    }

    public synchronized int dataRead(byte [] read){
        if(mSocket.isConnected() && NetworkActivity.isServer) {
            int len = serverThread.dataRead(read);
            return len;
        }
        if(mSocket.isConnected() && NetworkActivity.isClient) {
            int len = clientThread.dataRead(read);
            return len;
        }
        return -1;
    }

    public boolean isConneted(){
        if(mSocket==null){
            return false;
        }
        else return mSocket.isConnected();}

}

