package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/5/2.
 */

public class Rule {
    private int ruleId;
    private String combName;
    private int[] requiredCardId;
    private int requiredCardCount;
    private int alreadyHadCardCount;
    private boolean complete;
    private int basePoint;
    private int extraPoint;
    private boolean allowExtra;
    private boolean active = true;
    private int[] coveredRulesId;
    private int coveredRulesCount;
    /* if the combination allow you to collect more than required cards to gain extra point */

    public Rule(int id, String combName, int[] requiredCardId, int requiredCardCount, int basePoint,
                boolean allowExtra, int[] coveredRulesId, int coveredRulesCount) {
        alreadyHadCardCount = 0;
        complete = false;
        this.ruleId = id;
        this.basePoint = basePoint;
        this.extraPoint = 0;
        this.combName = combName;
        this.allowExtra = allowExtra;
        this.requiredCardCount = requiredCardCount;
        this.requiredCardId = new int[requiredCardCount];
        for (int i = 0; i < requiredCardCount; i++) {
            this.requiredCardId[i] = requiredCardId[i];
        }
        this.coveredRulesCount = coveredRulesCount;
        for (int i = 0; i < coveredRulesCount; i++) {
            this.coveredRulesId[i] = coveredRulesId[i];
        }
    }

    public boolean inCombination(int cardID) {
        for (int i = 0; i < requiredCardCount; i++) {
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

    public boolean isComplete() {return active && complete;}

    public int getPoint() {
        return basePoint + extraPoint;
    }

    public String getCombName() {
        return combName;
    }

    public int getCoveredRulesCount() {return coveredRulesCount;}

    public int getCoveredRuleId(int i) {return coveredRulesId[i];}

    public void cover() {active = false;}

    public int getRuleId() {
        return ruleId;
    }
}