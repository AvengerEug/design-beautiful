package com.eugene.sumarry.designbeautiful.mario;

/**
 * 事件枚举。
 * 把所有的事件去重，并且需要跟查表法中的表格对应上。
 */
public enum MarioEvent {
    // 获得蘑菇
    GOT_MUSHROOM(0),
    // 获得斗篷
    GOT_CAPE(1),
    // 获得火焰
    GOT_FIRE(2),
    // 遇到怪物
    MET_MONSTER(3);

    private int value;

    private MarioEvent(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
