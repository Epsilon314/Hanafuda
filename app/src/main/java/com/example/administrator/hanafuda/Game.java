package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Game {
    private int combRuleAmount;
    private Deck deck;
    private Field field;
    private Player player;
    private Player opponent;
    private Player activePlayer;
    private Combination[] combinationRule1;
    private Combination[] combinationRule2;
    private int initHandCardCount = 8;
    private int initFieldCardCount = 6;
    private boolean gameActive;

    public Game() {
        deck = new Deck();
        field = new Field();
        combRuleAmount = 5;
        combinationRule1 = new Combination[combRuleAmount];
        combinationRule2 = new Combination[combRuleAmount];
        //more rules to be done
        String[] combName = {"Gok","Shik","Bukku","SanKo","SongTongFangZhu"};
        int[][]requiredCardId = {
                {3,11,31,43,47},
                {3,11,31,47},
                {2,6,10,22,34,38},
                {3,11,31,47},
                {3,31,47}

        };
        int[] requiredCardCount = {5,4,6,3,3};
        int[] basePoint = {10,8,6,5,5};
        boolean[] allowExtra = {false,false,false,false,false};
        for (int i = 0; i < combRuleAmount; i++) {
            combinationRule1[i] = new Combination(combName[i], requiredCardId[i], requiredCardCount[i], basePoint[i], allowExtra[i]);
            combinationRule2[i] = new Combination(combName[i], requiredCardId[i], requiredCardCount[i], basePoint[i], allowExtra[i]);
        }
        player = new Player(0,combinationRule1);
        opponent = new Player(0,combinationRule2);
        activePlayer = player;
        gameActive = true;
    }

    public void grantCard(int number) {
        for (int i = 0; i < number; i++) {
            Card card = deck.DrawCard();
            activePlayer.playerDraw(card);
        }
    }

    public void gameStart() {
        grantCard(initHandCardCount);
        changeActiveplayer();
        grantCard(initHandCardCount);
        changeActiveplayer();
        for (int i = 0; i < initFieldCardCount; i++) {
            Card card = deck.DrawCard();
            field.fillField(card);
        }
    }

    public void playCardRound(int cardId) {
        Card playedCard = activePlayer.playerPlayCard(cardId);
        field.addCard(playedCard, activePlayer);
        Card drawCard = deck.DrawCard();
        field.addCard(drawCard, activePlayer);
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getPlayer() { return player;}

    public Player getOpponent() {return opponent;}

    public void changeActiveplayer() {
        if (activePlayer == player) activePlayer = opponent;
        else activePlayer = player;
    }

    public void changeRuleInitHandCount(int count) {
        initHandCardCount = count;
    }

    public void changeRuleInitFieldCount(int count) {
        initFieldCardCount = count;
    }

    public Field getField() {
        return field;
    }

    public int getDeckRemain() {
        return deck.getRemainCardNum();
    }

    public Boolean isPlayerActive() {
        return activePlayer == player;
    }

    public void endGame() {
        gameActive = false;
    }

    public boolean isGameActive()  {return gameActive;}

}
