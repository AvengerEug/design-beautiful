package com.eugene.sumarry.designbeautiful.mario.mario2;

import com.eugene.sumarry.designbeautiful.mario.MarioEvent;
import com.eugene.sumarry.designbeautiful.mario.MarioState;

import java.util.ArrayList;
import java.util.List;

import static com.eugene.sumarry.designbeautiful.mario.MarioEvent.*;
import static com.eugene.sumarry.designbeautiful.mario.MarioState.*;

/**
 * 马里奥的配置信息（查表法的表格）
 * 结合 {@link MarioEvent} 一起使用，Event内部定义的每个枚举对应的值，就是对应transitionTable和actionTable的横坐标
 * 结合 {@link MarioState} 一起使用，MarioState内部定义的每个枚举对应的值，就是对应transitionTable和actionTable的纵坐标
 */
public class MarioConfig {

    // 存储的是马里奥遇到所有事件，对应的状态转移和行为。
    // 举例：transitionTable[0][1] = 表示马里奥遇到了吃蘑菇的事件，变成了超级马里奥（一个行为动作）。

    public static final List<MarioItem> marioItems = new ArrayList<MarioItem>();

    static {
        marioItems.add(new MarioItem(SMALL, GOT_MUSHROOM, new MarioItem.MarioAction(SUPER, +100)));
        marioItems.add(new MarioItem(SMALL, GOT_CAPE, new MarioItem.MarioAction(SUPER, +200)));
        marioItems.add(new MarioItem(SMALL, GOT_FIRE, new MarioItem.MarioAction(FIRE, +300)));
        marioItems.add(new MarioItem(SMALL, MET_MONSTER, new MarioItem.MarioAction(SMALL, 0)));

        marioItems.add(new MarioItem(SUPER, GOT_MUSHROOM, null));
        marioItems.add(new MarioItem(SUPER, GOT_CAPE, new MarioItem.MarioAction(CAPE, +200)));
        marioItems.add(new MarioItem(SUPER, GOT_FIRE, new MarioItem.MarioAction(FIRE, +300)));
        marioItems.add(new MarioItem(SUPER, MET_MONSTER, new MarioItem.MarioAction(SMALL, -100)));

        marioItems.add(new MarioItem(CAPE, GOT_MUSHROOM, null));
        marioItems.add(new MarioItem(CAPE, GOT_CAPE, null));
        marioItems.add(new MarioItem(CAPE, GOT_FIRE, null));
        marioItems.add(new MarioItem(CAPE, MET_MONSTER, new MarioItem.MarioAction(SMALL, -200)));

        marioItems.add(new MarioItem(FIRE, GOT_MUSHROOM, null));
        marioItems.add(new MarioItem(FIRE, GOT_CAPE, null));
        marioItems.add(new MarioItem(FIRE, GOT_FIRE, null));
        marioItems.add(new MarioItem(FIRE, MET_MONSTER, new MarioItem.MarioAction(SMALL, -300)));

    }
//
//    private static final MarioState[][] transitionTable = {
//            {SUPER, CAPE, FIRE, SMALL},
//            {SUPER, CAPE, FIRE, SMALL},
//            {CAPE, CAPE, CAPE, SMALL},
//            {FIRE, FIRE, FIRE, SMALL}
//    };
//
//    // 行为表格，存储的是马里奥的所有事件，对应每个状态事件发生时，对应的行为
//    // 举个例子，当触发了transitionTable[0][1]事件），则会执行actionTable[0][1]的行为
//    public static final int[][] actionTable = {
//            {+100, +200, +300, +0},
//            {+0, +200, +300, -100},
//            {+0, +0, +0, -200},
//            {+0, +0, +0, -300}
//    };

}
