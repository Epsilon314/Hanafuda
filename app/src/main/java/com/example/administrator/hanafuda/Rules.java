package com.example.administrator.hanafuda;

/**
 * Created by Administrator on 2018/4/12.
 */
public class Rules {
    /**
     * rule set
     */
    private Rule[] rules;
    private int ruleCount;

    public Rules(int mRuleCount, Rule[] mRules) {
        ruleCount = mRuleCount;
        rules = new Rule[mRuleCount];
        for (int i = 0; i < ruleCount; i++) {
            rules[i] = mRules[i];
        }
    }

    public void checkAllRules(int cardID) {
        for (int i = 0; i < ruleCount; i++) {
            rules[i].inCombination(cardID);
            if(rules[i].isComplete()) {
                /**
                 * after a rule is completed, block rules indicated in coveredRuleId attribute
                 */
                for (int j = 0; j < rules[i].getCoveredRulesCount();j++) {
                    for (int k = 0; k < ruleCount; k++) {
                        if (rules[i].getCoveredRuleId(j) == rules[k].getRuleId()) rules[k].cover();
                    }
                }
            }
        }
    }

    public int sumUpPoints() {
        int points = 0;
        for (int i = 0; i < ruleCount; i++) {
            if(rules[i].isComplete()) points+=rules[i].getPoint();
        }
        return points;
    }

}

