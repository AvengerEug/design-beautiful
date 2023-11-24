package com.eugene.sumarry.designbeautiful.alert.one;

/**
 * @author muyang
 * @create 2023/11/24 21:02
 */
public class Alert {

    private Notification notification;

    public Alert(Notification notification) {
        this.notification = notification;
    }

    public void check(String api, long requestCount, long errorCount, long durationOfSeconds) {
        long tps = requestCount / durationOfSeconds;
        if (tps > AlertRule.getMatchedRule(api).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
        if (errorCount > AlertRule.getMatchedRule(api).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
    }
}
