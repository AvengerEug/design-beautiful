package com.eugene.sumarry.designbeautiful.mario.mario3;

import com.eugene.sumarry.designbeautiful.mario.MarioState;

/**
 * @author muyang
 * @create 2024/2/22 20:05
 */
public class SmallMario implements IMario {

    private MarioStateMachine marioStateMachine;

    public SmallMario(MarioStateMachine marioStateMachine) {
        this.marioStateMachine = marioStateMachine;
    }

    public MarioState getCurrentState() {
        return MarioState.SMALL;
    }

    public void obtainMushRoom() {
        // 变成超级马里奥
        marioStateMachine.setMarioState(new SuperMario(marioStateMachine));
        marioStateMachine.setScore(marioStateMachine.getScore() + 100);
    }

    public void obtainCape() {
        marioStateMachine.setMarioState(new CapMario(marioStateMachine));
        marioStateMachine.setScore(marioStateMachine.getScore() + 200);
    }

    public void obtainFireFlower() {
        marioStateMachine.setMarioState(new FireMario(marioStateMachine));
        marioStateMachine.setScore(marioStateMachine.getScore() + 300);
    }

    public void meetMonster() {
        // do nothing
    }
}
