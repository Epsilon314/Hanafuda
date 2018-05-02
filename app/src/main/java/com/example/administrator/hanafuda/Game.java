package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Game {
    private int ruleAmount;
    private Deck deck;
    private Field field;
    private Player player;
    private Player opponent;
    private Player activePlayer;
    private Rules rules1;
    private Rules rules2;
    private Rule[] rule1;
    private Rule[] rule2;
    private int initHandCardCount = 8;
    private int initFieldCardCount = 6;
    private boolean gameActive;

    public Game() {
        deck = new Deck();
        field = new Field();
        ruleAmount = 13;
        rule1 = new Rule[ruleAmount];
        rule2 = new Rule[ruleAmount];
        //Todo:more rules
        int[] ruleId = {0,1,2,3,4,5,6,7,8,9,10,11,12};
        String[] ruleName = {"Gok","Shik","Bukku","SanKo","SongTongFangZhu","Inoshikacho","Hanami-de ippai","Tsukimi-de ippai",
                             "Akatan","Aotan","Tan","Tane","Kasu"};
        int[][]requiredCardId = {
                {3,11,31,43,47},
                {3,11,31,47},
                {2,6,10,22,34,38},
                {3,11,31,47},
                {3,31,47},
                {23,27,39},//Inoshikacho
                {11,35},//Hanami-de ippai
                {31,35},//Tsukimi-de ippai
                {2,6,10},//Akatan
                {22,34,38},//Aotan
                {2,6,10,14,18,22,26,34,38,41},//Tan
                {7,15,19,23,27,30,35,39,42},//Tane
                {0,1,4,5,8,9,12,13,16,17,20,21,24,25,28,29,32,33,36,37,40,44,45,46}//Kasu

        };
        int[] requiredCardCount = {5,4,6,3,3,3,2,2,3,3,5,5,10};
        int[] basePoint = {10,8,6,5,5,5,3,3,3,3,1,1,1};
        boolean[] allowExtra = {false,false,false,false,false,false,false,false,false,false,true,true,true};
        int[] coveredRuleCount = {0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[][] coveredRuleId = {
                {}, {}, {}, {}, {}, {}, {},{},{},{},{},{},{}
        };//Todo:this is not checked, simply all assign blank, actually some rules may cover other rules and need to be edited

        for (int i = 0; i < ruleAmount; i++) {
            rule1[i] = new Rule(ruleId[i],ruleName[i], requiredCardId[i], requiredCardCount[i],
                    basePoint[i], allowExtra[i],coveredRuleId[i],coveredRuleCount[i]);
            rule2[i] = new Rule(ruleId[i],ruleName[i], requiredCardId[i], requiredCardCount[i],
                    basePoint[i], allowExtra[i],coveredRuleId[i],coveredRuleCount[i]);
        }
        rules1 = new Rules(ruleAmount,rule1);
        rules2 = new Rules(ruleAmount,rule2);
        player = new Player(0, rules1);
        opponent = new Player(0, rules2);
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
