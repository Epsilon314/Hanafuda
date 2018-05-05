package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Player {
    /**
     * player has two card region, hand and owned
     * each player store their rules separately
     */
    private Card[] handCard;
    private Card[] ownCard;
    private int handCount;
    private int ownCount;
    private int point;
    private Rules rules;
    /**
     * once you complete a combination, you can end the game
     * while you can also choose not to end the game aiming at more points
     * in this case you must complete another combination to regain the right to end the game
     */
    private boolean meetGameEndRequirements;

    public Player(int startPoint, Rules mRules) {
        handCard = new Card[8];
        ownCard = new Card[48];
        handCount = 0;
        point = startPoint;
        rules = mRules;
    }

    public void playerDraw(Card drawCard) {
        handCard[handCount++] = drawCard;
    }

    public  Card playerPlayCard(int handCardByIdx) {
         if (handCardByIdx >= handCount) return null;
         else {
              Card pCard = handCard[handCardByIdx];
              for (int i = handCardByIdx; i < handCount - 1; i++) {
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
        /**
         * whenever got a new card, update the combination state
         */
        int newPoint = 0;
        rules.checkAllRules(newOwnedCard.getId());
        newPoint = rules.sumUpPoints();
        if (newPoint > point) {
            point = newPoint;
            meetGameEndRequirements = true;
        }
    }

    public int getHandCount() {
        return handCount;
    }

    public int getOwnCount() {return ownCount;}

    public Card getHandCardByIdx(int idx) {
        if (idx < handCount) return handCard[idx];
        else return null;
    }

    public Card getOwnCardByIdx(int idx) {
        if (idx < ownCount) return ownCard[idx];
        else return null;
    }

    public int getPoint() {
        return point;
    }

    public void giveUpEndGame() {
        meetGameEndRequirements = false;
    }

    public boolean isMeetGameEndRequirements() {
        return meetGameEndRequirements;
    }
}
