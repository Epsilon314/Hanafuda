package com.example.administrator.hanafuda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Bitmap[] allCardFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allCardFace = new Bitmap[48];
        allCardFace[0] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface10);
        allCardFace[1] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface11);
        allCardFace[2] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface12);
        allCardFace[3] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface13);
        allCardFace[4] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface20);
        allCardFace[5] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface21);
        allCardFace[6] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface22);
        allCardFace[7] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface23);
        allCardFace[8] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface30);
        allCardFace[9] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface31);
        allCardFace[10] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface32);
        allCardFace[11] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface33);
        allCardFace[12] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface40);
        allCardFace[13] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface41);
        allCardFace[14] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface42);
        allCardFace[15] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface43);
        allCardFace[16] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface50);
        allCardFace[17] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface51);
        allCardFace[18] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface52);
        allCardFace[19] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface53);
        allCardFace[20] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface60);
        allCardFace[21] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface61);
        allCardFace[22] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface62);
        allCardFace[23] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface63);
        allCardFace[24] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface70);
        allCardFace[25] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface71);
        allCardFace[26] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface72);
        allCardFace[27] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface73);
        allCardFace[28] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface80);
        allCardFace[29] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface81);
        allCardFace[30] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface82);
        allCardFace[31] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface83);
        allCardFace[32] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface90);
        allCardFace[33] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface91);
        allCardFace[34] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface92);
        allCardFace[35] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface93);
        allCardFace[36] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface100);
        allCardFace[37] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface101);
        allCardFace[38] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface102);
        allCardFace[39] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface103);
        allCardFace[40] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface110);
        allCardFace[41] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface111);
        allCardFace[42] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface112);
        allCardFace[43] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface113);
        allCardFace[44] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface120);
        allCardFace[45] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface121);
        allCardFace[46] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface122);
        allCardFace[47] = BitmapFactory.decodeResource(getResources(),R.drawable.cardface123);


    }



}
