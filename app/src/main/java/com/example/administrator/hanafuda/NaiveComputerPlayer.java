package com.example.administrator.hanafuda;

import java.util.Random;

/**
 * Created by Administrator on 2018/4/25.
 */

public class NaiveComputerPlayer {

    public NaiveComputerPlayer() {}
    void randomPlayCard(Game currentGame) {
        if (currentGame.isGameActive()) {
            int handCount = currentGame.getActivePlayer().getHandCount();
            Random rand = new Random();
            int playCardIdx = rand.nextInt(handCount);
            currentGame.playCardRound(playCardIdx);
        }
    }

    void chooseEndGame(Game currentGame) {
        if (currentGame.isGameActive()) {
            currentGame.endGame();
        }
    }
}
