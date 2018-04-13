package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/4/12.
 */

public class Combination {
    private String combName;
    private int[] requiredCardId;
    private int requiredCardCount;
    private int alreadyHadCardCount;
    private boolean complete;
    private int basePoint;
    private int extraPoint;
    private boolean allowExtra;
    /* if the combination allow you to collect more than required cards to gain extra point */

    public Combination(String combName, int[] requiredCardId, int requiredCardCount, int basePoint, boolean allowExtra) {
        alreadyHadCardCount = 0;
        complete = false;
        this.basePoint = basePoint;
        this.extraPoint = 0;
        this.combName = combName;
        this.allowExtra = allowExtra;
        this.requiredCardCount = requiredCardCount;
        this.requiredCardId = new int[requiredCardCount];
        for (int i = 0; i < requiredCardCount; i++) {
            this.requiredCardId[i] = requiredCardId[i];
        }
    }

    public boolean inCombination(int cardID) {
        for (int i =0; i < requiredCardCount; i++) {
            if (requiredCardId[i] == cardID) {
                alreadyHadCardCount++;
                update();
                return true;
            }
        }
        return false;
    }

    private void update() {
        if (alreadyHadCardCount == requiredCardCount) complete = true;
        if (complete && allowExtra) {
            extraPoint = alreadyHadCardCount - requiredCardCount;
        }
    }

    public boolean isComplete() {
        return complete;
    }

    public int getPoint() {
        return basePoint + extraPoint;
    }

    public String getCombName() {
        return combName;
    }
}
