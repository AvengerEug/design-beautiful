package com.eugene.sumarry.designbeautiful.mario.model1;

import com.eugene.sumarry.designbeautiful.mario.MarioState;

/**
 * 分支逻辑法
 * @author avengerEug
 * @create 2024/2/5 07:35
 */
public class MarioStateMachine {

    // 马里奥状态
    private MarioState currentState;
    // 积分
    private Integer score;


    // 初始状态：小马里奥
    public MarioStateMachine() {
        this.score = 0;
        this.currentState = MarioState.SMALL;
    }

    /**
     * 对应每一个马里奥的事件：获取蘑菇
     */
    public void obtainMushRoom() {
        // 表格内容，获取蘑菇事件，一共有一种case，就是小马里奥变成超级马里奥
        if (currentState == MarioState.SMALL) {
            // 积分加100
            this.score += 100;
            // 状态变成超级马里奥
            this.currentState = MarioState.SUPER;
        }
    }

    /**
     * 对应每一个马里奥的事件：获取斗篷
     */
    public void obtainCape() {
        // 获取斗篷一共有两种case：
        // case1: 小马里奥获取斗篷，变成斗篷马里奥，并且加200积分
        // case2: 超级马里奥获取斗篷，变成斗篷马里奥，并且加200积分
        if (this.currentState == MarioState.SMALL || this.currentState == MarioState.SUPER) {
            // 积分加200
            this.score += 200;
            // 状态变成超级马里奥
            this.currentState = MarioState.CAPE;
        }
    }

    /**
     * 获取火焰
     */
    public void obtainFireFlower() {
        // 获取火焰一共有两种case：
        // case1: 小马里奥获取火焰，变成火焰马里奥，并且加300积分
        // case2: 超级马里奥获取火焰，变成火焰马里奥，并且加300积分
        if (this.currentState == MarioState.SMALL || this.currentState == MarioState.SUPER) {
            // 积分加300
            this.score += 300;
            // 状态变成超级马里奥
            this.currentState = MarioState.FIRE;
        }
    }

    /**
     * 遇到怪物
     */
    public void meetMonster() {
        // 获取怪物一共有三种case：
        // case1: 超级马里奥遇到怪物，变成小马里奥，并且减100积分
        if (this.currentState == MarioState.SUPER) {
            this.score -= 100;
            this.currentState = MarioState.SMALL;
        }

        // case2: 斗篷马里奥遇到怪物，变成小马里奥，并且减200积分
        if (this.currentState == MarioState.CAPE) {
            this.score -= 200;
            this.currentState = MarioState.SMALL;
        }

        // case3: 火焰马里奥遇到怪物，变成小马里奥，并且减300积分
        if (this.currentState == MarioState.FIRE) {
            this.score -= 300;
            this.currentState = MarioState.SMALL;
        }

    }

    public int getScore() {
        return this.score;
    }

    public MarioState getCurrentState() {
        return this.currentState;
    }

}
