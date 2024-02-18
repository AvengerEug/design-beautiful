package com.eugene.sumarry.designbeautiful.mario.mario2;

import com.eugene.sumarry.designbeautiful.mario.MarioEvent;
import com.eugene.sumarry.designbeautiful.mario.MarioState;

/**
 * @author muyang
 * @create 2024/2/18 17:41
 */
public class MarioItem {

    // 当前的状态
    private MarioState currentState;

    // 遇到的事件
    private MarioEvent marioEvent;

    // 对应执行的行为
    private MarioAction action;


    public static class MarioAction {
        // 要变成的新状态
        private MarioState targetMarioState;

        // 对应的积分
        private int score;

        public MarioState getTargetMarioState() {
            return targetMarioState;
        }

        public void setTargetMarioState(MarioState targetMarioState) {
            this.targetMarioState = targetMarioState;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public MarioAction(MarioState targetMarioState, int score) {
            this.targetMarioState = targetMarioState;
            this.score = score;
        }
    }

    public MarioState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(MarioState currentState) {
        this.currentState = currentState;
    }

    public MarioEvent getMarioEvent() {
        return marioEvent;
    }

    public void setMarioEvent(MarioEvent marioEvent) {
        this.marioEvent = marioEvent;
    }

    public MarioAction getAction() {
        return action;
    }

    public void setAction(MarioAction action) {
        this.action = action;
    }

    public MarioItem(MarioState currentState, MarioEvent marioEvent, MarioAction action) {
        this.currentState = currentState;
        this.marioEvent = marioEvent;
        this.action = action;
    }
}
