package com.eugene.sumarry.designbeautiful.mario.mario3;

/**
 * @author muyang
 * @create 2024/2/23 10:32
 */
public class Entry {

    public static void main(String[] args) {
        // 初始化状态机：内部的马里奥状态为小马里奥
        MarioStateMachine marioStateMachine = new MarioStateMachine();

        // 获取火焰： +300 & 变身火焰马里奥
        marioStateMachine.getMarioState().obtainFireFlower();
        System.out.println(marioStateMachine);

        // 获取斗篷：啥事不干
        marioStateMachine.getMarioState().obtainCape();
        System.out.println(marioStateMachine);

        // 遇到怪物：-300 & 变成小马里奥
        marioStateMachine.getMarioState().meetMonster();
        System.out.println(marioStateMachine);

        // 获取斗篷：+200 & 变成斗篷马里奥
        marioStateMachine.getMarioState().obtainCape();
        System.out.println(marioStateMachine);

        // 遇到怪物：-200 & 变成小马里奥
        marioStateMachine.getMarioState().meetMonster();
        System.out.println(marioStateMachine);

        // 获取蘑菇：+100 & 变成超级马里奥
        marioStateMachine.getMarioState().obtainMushRoom();
        // 获取斗篷：+200 & 变成斗篷马里奥
        marioStateMachine.getMarioState().obtainCape();
        // 获取火焰：啥事不干
        marioStateMachine.getMarioState().obtainFireFlower();
        // 遇到怪物：-300 & 变成小马里奥  （剩余0积分）
        marioStateMachine.getMarioState().meetMonster();
    }


}
