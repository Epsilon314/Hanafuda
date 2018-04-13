package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Player {
    private Card[] handCard;
    private Card[] ownCard;
    private int handCount;
    private int ownCount;
    private int point;
    private int ownValue;
    private Combination[] combinations;
    private int combCount;

    public Player(int startpoint, Combination[] combinations) {
        handCard = new Card[8];
        ownCard = new Card[48];
        ownValue = 0;
        handCount = 0;
        point = startpoint;
        combCount = combinations.length;
        this.combinations = new Combination[combCount];
        for (int i = 0; i < combCount; i++) {
            this.combinations[i] = combinations[i];
        }
    }

    public void playerDraw(Card drawCard) {
        handCard[handCount++] = drawCard;
    }

    public  Card playerPlayCard(int handCardByIdx) {
         if (handCardByIdx >= handCount) return null;
         else {
              Card pCard = handCard[handCardByIdx];
              for (int i = handCardByIdx; i < handCount; i++) {
                  handCard[i] = handCard[i + 1];
              }
              handCount--;
              return  pCard;
         }
    }

    public void playerGive(Card giveCard) {
        ownCard[ownCount++] = giveCard;
        updateOwnedValue(giveCard);
    }

    public void updateOwnedValue(Card newOwnedCard) {
        //whenever got a new card, update the combination state
        point = 0;
        for (int i = 0; i < combCount; i++) {
            combinations[i].inCombination(newOwnedCard.getId());
            if(combinations[i].isComplete()) point += combinations[i].getPoint();
        }
    }
}
