package com.eugene.sumarry.designbeautiful.mario.mario3;

/**
 * 马里奥的状态机
 * @author muyang
 * @create 2024/2/22 20:04
 */
public class MarioStateMachine {

    /**
     * 当前状态
     */
    private IMario marioState;

    /**
     * 积分
     */
    private Integer score;

    public MarioStateMachine() {
        this.score = 0;
        this.marioState = new SmallMario(this);
    }

    public IMario getMarioState() {
        return marioState;
    }

    public void setMarioState(IMario marioState) {
        this.marioState = marioState;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "MarioStateMachine{" +
                "marioState=" + marioState +
                ", score=" + score +
                '}';
    }


}
