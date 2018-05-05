package com.example.administrator.hanafuda;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;
/**
 * Created by Administrator on 2018/4/11.
 */

public class Deck {
    public static final int DECKMAX = 48;
    private Card[] dcard;
    private int remainCardNum;

    public Deck() {
        /**
         * create a deck
         */
        dcard = new Card[DECKMAX];
        remainCardNum = DECKMAX;
    }

    public void initDeck() {
        /**
         * init the deck with 48 cards and shuffle
         */
        for (int m = 1; m <= 12; m++) {
            for (int d = 0; d <=3; d++) {
                dcard[4*m-4+d] = new Card(m,d);
            }
        }
        Shuffle();
        Shuffle();
        Shuffle();
        /**
         * important things do three times
         */
    }

    public void copyDeck(GameMessage.initMessage msg) {
        dcard = new Card[DECKMAX];
        remainCardNum = DECKMAX;
        for (int i = 0; i < DECKMAX; i++) {
            int id = msg.getDeckCardIdByIdx(i);
            int d = id % 4;
            int m = (id - d + 4) / 4;
            dcard[i] = new Card(m,d);
        }
    }

    public Card DrawCard() {
        /**
         * draw card from deck
         * return the card drown if the deck is not empty, otherwise return null
         */
        if (!isEmpty()) {
            return dcard[--remainCardNum];
        }
        return null;
    }

    public boolean isEmpty() {
        return remainCardNum == 0;
    }

    private void Shuffle() {
        int lastIdx = remainCardNum - 1;
        int swapIdx;
        Card swapCard;
        Random rand = new Random();
        while (lastIdx > 1) {
            swapIdx = rand.nextInt(lastIdx);
            swapCard = dcard[swapIdx];
            dcard[swapIdx] = dcard[lastIdx];
            dcard[lastIdx] = swapCard;
            lastIdx--;
        }
    }

    public int getRemainCardNum() {
        return remainCardNum;
    }

    public int getCardIdByIdx(int idx) {
        return dcard[idx].getId();
    }
}
