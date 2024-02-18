package com.eugene.sumarry.designbeautiful.mario.mario2;

/**
 * 查表法。核心是，如何把表格构建出来（即如何将MarioState 和 MarioEvent枚举的值与表格对应上来）
 *
 */
public class Entry {

    public static void main(String[] args) {
        MarioStateMachine marioStateMachine = new MarioStateMachine();
        // 吃蘑菇
        marioStateMachine.obtainMushRoom();
        System.out.println(marioStateMachine);
        // 获取斗篷
        marioStateMachine.obtainCape();
        System.out.println(marioStateMachine);
        // 遇见怪物
        marioStateMachine.meetMonster();
        System.out.println(marioStateMachine);
        // 遇见怪物
        marioStateMachine.meetMonster();
        System.out.println(marioStateMachine);
    }
}
