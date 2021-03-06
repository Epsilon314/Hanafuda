package com.example.administrator.hanafuda;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2018/4/10.
 */

public class Card {
    /**
     * a hanafuda card has two values, whose combination is unique
     */
    private int month;
    private int detail;
    private int id;
    /**
     * get image by id
     */

    public Card(int nMonth, int nDetail) {
        month = nMonth;
        detail = nDetail;
        /**
         * id is unique for each card and you can retrieve month and detail from id
         */
        id = month * 4 + detail - 4;
    }

    public int getMonth() {return month;}
    public int getDetail() {return detail;}
    public int getId() {return id;}

}

