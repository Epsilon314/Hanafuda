package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Field {
    private  Card[] fieldCard;
    private int fieldCardNum;
    private int fieldCardMax = 16;
    /**
     * currently we assume field card will not exceed 16, which is guaranteed by game rules
     */

    public Field() {
        /**
         * start from a empty field
         */
        fieldCard = new Card[fieldCardMax];
        fieldCardNum = 0;
    }

    public boolean isEmpty() {
        return fieldCardNum == 0;
    }

    public void rmCard(int rmCardByIdx) {
        /**
         * remove card from field
         */
        if (rmCardByIdx < fieldCardNum) {
            Card rmCard = fieldCard[rmCardByIdx];
            for (int i = rmCardByIdx; i < fieldCardNum - 1; i++) {
                fieldCard[i] = fieldCard[i+1];
            }
            fieldCardNum--;
        }
    }

    public boolean fillField(Card newCard) {
        /**
         * put card on field, don't do any other work
         */
        if (fieldCardNum == fieldCardMax) return false;
        fieldCard[fieldCardNum++] = newCard;
        return true;
    }

    public boolean addCard(Card newCard, Player activePlayer) {
        /**
         * add a card to the field, and check if it led to a combo
         */
        if (fieldCardNum == fieldCardMax) return false;
        int[] possibleCombo = new int[4];
        int comboCount = 0;
        if (isEmpty()) fieldCard[fieldCardNum++] = newCard;
        else {
            for (int i =0; i < fieldCardNum; i++) {
                if (newCard.getMonth() == fieldCard[i].getMonth()) {
                    possibleCombo[comboCount++] = i;
                }
            }
            if (comboCount == 0) fieldCard[fieldCardNum++] = newCard;
            else if (comboCount == 1) {
                activePlayer.playerGive(fieldCard[possibleCombo[0]]);
                activePlayer.playerGive(newCard);
                this.rmCard(possibleCombo[0]);
            }
            else {
                /**
                 * Todo:get player selection
                 * now we simply choose a card for the player
                 */
                activePlayer.playerGive(fieldCard[possibleCombo[0]]);
                activePlayer.playerGive(newCard);
                this.rmCard(possibleCombo[0]);
            }
        }
        return true;
    }

    public int getCardIdByIdx(int idx) {
        return fieldCard[idx].getId();
    }

    public int getFieldCardCount() {
        return fieldCardNum;
    }

}