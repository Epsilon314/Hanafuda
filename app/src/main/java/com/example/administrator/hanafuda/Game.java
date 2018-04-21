package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Game {
    private int combRuleAmount = 4;
    private Deck deck;
    private Field field;
    private Player player1;
    private Player player2;
    private Player activeplayer;
    private Combination[] combinationRule;
    private int initHandCardCount = 8;
    private int initFieldCardCount = 6;

    public Game() {
        deck = new Deck();
        field = new Field();
        combinationRule = new Combination[combRuleAmount];
        //more rules to be done
        String[] combName = {"Gok","Shik","Ame-Shiko","Bukku"};
        int[][]requiredCardId = {
                {3,11,31,43,47},
                {3,11,31,47},
                {3,11,31,43,47},
                {2,6,10,22,34,38}
        };
        int[] requiredCardCount = {5,4,4,6};
        int[] basePoint = {10,8,7,6};
        boolean[] allowExtra = {false,false,false,false};
        for (int i = 0; i < combRuleAmount; i++) {
            combinationRule[i] = new Combination(combName[i], requiredCardId[i], requiredCardCount[i], basePoint[i], allowExtra[i]);
        }
        player1 = new Player(0,combinationRule);
        player2 = new Player(0,combinationRule);
        activeplayer = player1;
    }

    public void grantCard(int number) {
        for (int i = 0; i < number; i++) {
            Card card = deck.DrawCard();
            activeplayer.playerDraw(card);
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
        Card playedCard = activeplayer.playerPlayCard(cardId);
        field.addCard(playedCard,activeplayer);
        Card drawCard = deck.DrawCard();
        field.addCard(drawCard,activeplayer);
    }

    public Player getActivePlayer() {
        return activeplayer;
    }

    public void changeActiveplayer() {
        if (activeplayer == player1) activeplayer = player2;
        else activeplayer = player1;
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
}
