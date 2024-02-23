package com.eugene.sumarry.designbeautiful.mario.mario3;

import com.eugene.sumarry.designbeautiful.mario.MarioState;

/**
 * 定义马里奥相关的事件接口。
 * 每个事件对应的action，在实现IMario接口的马里奥类下
 * @author muyang
 * @create 2024/2/22 20:02
 */
public interface IMario {

    MarioState getCurrentState();

    // 定义的事件
    // 获取蘑菇
    void obtainMushRoom();
    // 获取斗篷
    void obtainCape();
    // 获取火焰
    void obtainFireFlower();
    // 遇到怪物
    void meetMonster();




}
