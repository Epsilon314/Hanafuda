package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Field {
    private  Card[] fieldCard;
    private int fieldCardNum;
    private int fieldCardMax = 16;

    public Field() {
        fieldCard = new Card[fieldCardMax];
        fieldCardNum = 0;
    }

    public boolean isEmpty() {
        return fieldCardNum == 0;
    }

    public void rmCard(int rmCardByIdx) {
        if (rmCardByIdx < fieldCardNum) {
            Card rmCard = fieldCard[rmCardByIdx];
            for (int i = rmCardByIdx; i < fieldCardNum - 1; i++) {
                fieldCard[i] = fieldCard[i+1];
            }
            fieldCardNum--;
        }
    }

    public boolean fillField(Card newCard) {
        if (fieldCardNum == fieldCardMax) return false;
        fieldCard[fieldCardNum++] = newCard;
        return true;
    }

    public boolean addCard(Card newCard, Player activePlayer) {
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
                //get player selection
                //to be done
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