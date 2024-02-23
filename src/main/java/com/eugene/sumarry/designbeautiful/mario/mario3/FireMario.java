package com.eugene.sumarry.designbeautiful.mario.mario3;

import com.eugene.sumarry.designbeautiful.mario.MarioState;

/**
 * @author muyang
 * @create 2024/2/22 20:05
 */
public class FireMario implements IMario {

    private MarioStateMachine marioStateMachine;

    public FireMario(MarioStateMachine marioStateMachine) {
        this.marioStateMachine = marioStateMachine;
    }

    public MarioState getCurrentState() {
        return MarioState.SMALL;
    }

    public void obtainMushRoom() {
        // do nothing
    }

    public void obtainCape() {
        // do nothing
    }

    public void obtainFireFlower() {
        // do nothing
    }

    public void meetMonster() {
        // 遇到怪物，变成小马里奥
        marioStateMachine.setMarioState(new SmallMario(marioStateMachine));
        // 扣100积分
        marioStateMachine.setScore(marioStateMachine.getScore() - 300);
    }
}
