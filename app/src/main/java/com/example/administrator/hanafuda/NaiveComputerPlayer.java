package com.example.administrator.hanafuda;

import java.util.Random;

/**
 * Created by Administrator on 2018/4/25.
 */

public class NaiveComputerPlayer {

    /**
     * computer players
     * only a naive one used for displaying the game process, also be used as a weak opponent for newbies
     * Todo:some strong computer players
     */

    public NaiveComputerPlayer() {}
    void randomPlayCard(Game currentGame) {
        /**
         * just play hand cards by random
         */
        if (currentGame.isGameActive()) {
            int handCount = currentGame.getActivePlayer().getHandCount();
            Random rand = new Random();
            int playCardIdx = rand.nextInt(handCount);
            currentGame.playCardRound(playCardIdx);
        }
    }

    void chooseEndGame(Game currentGame) {
        /**
         * choose to end the game once possible
         */
        if (currentGame.isGameActive()) {
            currentGame.endGame();
        }
    }
}
