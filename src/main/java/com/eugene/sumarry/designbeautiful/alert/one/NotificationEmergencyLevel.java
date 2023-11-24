package com.eugene.sumarry.designbeautiful.alert.one;

/**
 * @author muyang
 * @create 2023/11/24 21:07
 */
public enum NotificationEmergencyLevel {

    SEVERE("严重"),
    URGENCY("紧急"),
    NORMAL("普通"),
    TRIVIAL("无关紧要")
    ;


    private String name;

    NotificationEmergencyLevel(String name) {
        this.name = name;
    }
}
