package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Game {
    private int combRuleAmount = 20;
    private Deck deck;
    private Field field;
    private Player player1;
    private Player player2;
    private Combination[] combinationRule;
    private int combRuleCount;

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
            combinationRule[i] = new Combination(combName[i], requiredCardId[1], requiredCardCount[i], basePoint[i], allowExtra[i]);
        }
        player1 = new Player(0,combinationRule);
        player2 = new Player(0,combinationRule);
    }


}
