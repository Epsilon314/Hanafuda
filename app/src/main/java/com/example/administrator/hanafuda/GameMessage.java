package com.example.administrator.hanafuda;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/4.
 */

public class GameMessage {

    public class initMessage implements Serializable {
        private static final long serialVersionUID = 1L;
        public static final transient int YOUFIRST = 0;
        public static final transient int MEFIRST = 1;
        private int[] deck;
        private int whoFirst;

        public initMessage(int whoFirst) {
            deck = new int[Deck.DECKMAX];
            this.whoFirst = whoFirst;
        }

        public void writeDeckCardIdByIdx(int idx, int id) {
            deck[idx] = id;
        }


        public int getDeckCardIdByIdx(int idx) {
            return deck[idx];
        }

        public int getWhoFirst() {
            return whoFirst;
        }

    }

    public class stepMessage implements Serializable {
        private static final long serialVersionUID = 2L;
        private int playedCardId;

        public stepMessage(int playedCardId) {
            this.playedCardId = playedCardId;
        }

        public int getPlayedCardId() {
            return playedCardId;
        }
    }
}
