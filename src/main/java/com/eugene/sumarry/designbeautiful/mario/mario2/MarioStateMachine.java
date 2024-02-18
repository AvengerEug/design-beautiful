package com.eugene.sumarry.designbeautiful.mario.mario2;

import com.eugene.sumarry.designbeautiful.mario.MarioEvent;
import com.eugene.sumarry.designbeautiful.mario.MarioState;

/**
 * 马里奥状态机。查表法。
 */
public class MarioStateMachine {
    private int score;

    private MarioState currentState;


    // 初始状态，小马里奥
    public MarioStateMachine() {
        this.score = 0;
        this.currentState = MarioState.SMALL;
    }

    // 获取蘑菇, 执行获取蘑菇的事件
    public void obtainMushRoom() {
        executeEvent(MarioEvent.GOT_MUSHROOM);
    }

    public void obtainCape() {
        executeEvent(MarioEvent.GOT_CAPE);
    }

    public void obtainFireFlower() {
        executeEvent(MarioEvent.GOT_FIRE);
    }

    public void meetMonster() {
        executeEvent(MarioEvent.MET_MONSTER);
    }

    private void executeEvent(MarioEvent event) {
        for (MarioItem marioItem : MarioConfig.marioItems) {
            // 都是枚举，可以直接等号判断
            if (marioItem.getCurrentState() == this.currentState && marioItem.getMarioEvent() == event) {
                // 要执行的动作
                MarioItem.MarioAction action = marioItem.getAction();
                if (action == null) {
                    // 有事件，无行为
                    continue;
                }
                // 1、更新状态枚举
                this.currentState = action.getTargetMarioState();
                // 2、更新分数
                this.score += action.getScore();
            }
        }
    }


    @Override
    public String toString() {
        return "MarioStateMachine{" +
                "score=" + score +
                ", currentState=" + currentState +
                '}';
    }
}
