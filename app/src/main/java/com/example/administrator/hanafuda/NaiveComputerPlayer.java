package com.example.administrator.hanafuda;

import java.util.Random;

/**
 * Created by Administrator on 2018/4/25.
 */

public class NaiveComputerPlayer {

    public NaiveComputerPlayer() {}
    void playCard(Game currentGame) {
        int handCount = currentGame.getActivePlayer().getHandCount();
        Random rand = new Random();
        int playCardIdx = rand.nextInt(handCount);
        currentGame.playCardRound(playCardIdx);
    }
}
