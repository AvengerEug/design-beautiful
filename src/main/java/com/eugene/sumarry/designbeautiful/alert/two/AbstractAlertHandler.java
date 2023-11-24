package com.eugene.sumarry.designbeautiful.alert.two;

/**
 * @author muyang
 * @create 2023/11/24 21:25
 */
public abstract class AbstractAlertHandler implements AlertHandler {

    private Notification notification;

    public AbstractAlertHandler(Notification notification) {
        this.notification = notification;
    }
}
