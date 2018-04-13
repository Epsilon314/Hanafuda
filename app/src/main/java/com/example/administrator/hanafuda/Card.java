package com.example.administrator.hanafuda;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2018/4/10.
 */

public class Card {
    private int month;
    private int detail;
    private int id;
    //get image by id

    public Card(int nMonth, int nDetail) {
        month = nMonth;
        detail = nDetail;
        id = month * 4 + detail - 4;
    }

    public int getMonth() {return month;}
    public int getDetail() {return detail;}
    public int getId() {return id;}

}

