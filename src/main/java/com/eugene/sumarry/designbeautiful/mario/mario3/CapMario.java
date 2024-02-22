package com.eugene.sumarry.designbeautiful.mario.mario3;

import com.eugene.sumarry.designbeautiful.mario.MarioState;

/**
 * @author muyang
 * @create 2024/2/22 20:05
 */
public class CapMario implements IMario {

    private MarioStateMachine marioStateMachine;

    public CapMario(MarioStateMachine marioStateMachine) {
        this.marioStateMachine = marioStateMachine;
    }

    public MarioState getCurrentState() {
        return MarioState.SMALL;
    }

    public void obtainMushRoom() {
        // 变成超级马里奥
    }

    public void obtainCape() {
    }

    public void obtainFireFlower() {
    }

    public void meetMonster() {
        // do nothing
    }
}
