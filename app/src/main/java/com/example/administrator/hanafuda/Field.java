package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Field {
    private  Card[] fieldCard;
    private int fieldCardNum;

    public Field() {
        fieldCard = new Card[16];
        fieldCardNum = 0;
    }

    public boolean isEmpty() {
        return fieldCardNum == 0;
    }

    public void rmCard(int rmCardByIdx) {
        if (rmCardByIdx < fieldCardNum) {
            Card rmCard = fieldCard[rmCardByIdx];
            for (int i = rmCardByIdx; i < fieldCardNum; i++) {
                fieldCard[i] = fieldCard[i+1];
            }
            fieldCardNum--;
        }
    }

    public void addCard(Card newCard, Player activePlayer) {
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
    }

}