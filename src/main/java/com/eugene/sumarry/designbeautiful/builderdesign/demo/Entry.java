package com.eugene.sumarry.designbeautiful.builderdesign.demo;

/**
 * @author muyang
 * @create 2023/12/29 15:45
 */
public class Entry {

    public static void main(String[] args) {
        RedisPoolConfig redisPoolConfig = new RedisPoolConfig.Builder()
                .setName("customerName")
                .setMaxIdle(7)
                .setMinIdle(8)
                .build();

    }

}
