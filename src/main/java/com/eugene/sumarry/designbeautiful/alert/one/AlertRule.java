package com.eugene.sumarry.designbeautiful.alert.one;

/**
 * @author muyang
 * @create 2023/11/24 21:01
 */
public class AlertRule {


    public static AlertRule getMatchedRule(String apiName) {
        return new AlertRule();
    }

    public long getMaxTps() {
        return 100;
    }

    public long getMaxErrorCount() {
        return 100;
    }

}
