package com.eugene.sumarry.designbeautiful.mario.mario3;

import com.eugene.sumarry.designbeautiful.mario.MarioState;

/**
 * 超级马里奥状态
 * @author muyang
 * @create 2024/2/22 20:05
 */
public class SuperMario implements IMario {

    private MarioStateMachine marioStateMachine;

    public SuperMario(MarioStateMachine marioStateMachine) {
        this.marioStateMachine = marioStateMachine;
    }

    public MarioState getCurrentState() {
        return MarioState.SUPER;
    }

    public void obtainMushRoom() {
        // 吃蘑菇，不做啥事
    }

    public void obtainCape() {
        // 获取斗篷, 变身斗篷马里奥
        marioStateMachine.setMarioState(new CapMario(marioStateMachine));
        // 加200积分
        marioStateMachine.setScore(marioStateMachine.getScore() + 200);
    }

    public void obtainFireFlower() {
        // 获取火焰，变成火焰马里奥
        marioStateMachine.setMarioState(new FireMario(marioStateMachine));
        // 加300积分
        marioStateMachine.setScore(marioStateMachine.getScore() + 300);
    }

    public void meetMonster() {
        // 遇到怪物，变成小马里奥
        marioStateMachine.setMarioState(new SmallMario(marioStateMachine));
        // 扣100积分
        marioStateMachine.setScore(marioStateMachine.getScore() - 100);
    }
}
