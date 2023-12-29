package com.eugene.sumarry.designbeautiful.builderdesign.demo;

import org.apache.commons.lang3.StringUtils;

/**
 * 总结：
 * 使用建造者模式，就是利用build对象去构建所有的属性，在每一个属性的构建中有自己的基础校验。
 * 当使用builder对象的build方法构建配置对象(此案例就是RedisPoolConfig)时，再做全局的业务校验。
 * 比如某些字段的值不能大于某些字段等规则。
 * 同时，配置对象的构建函数需要设置成私有的，并且入参为build对象，统一从builder对象中获取入参来初始化
 * 配置对象。
 *
 *
 * @create 2023/12/29 15:29
 */
public class RedisPoolConfig {

    // 是必填项
    private String name;

    // 非必填项，最大空闲默认为8，最小空闲数默认为2
    private int maxIdle;
    private int minIdle;

    private RedisPoolConfig(Builder builder) {
        this.name = builder.name;
        this.maxIdle = builder.maxIdle;
        this.minIdle = builder.minIdle;
    }


    /**
     * 建造者对象，内部提供对RedisPoolConfig参数赋值、校验的逻辑
     */
    public static class Builder {
        private static final int DEFAULT_MAX_IDLE = 8;
        private static final int DEFAULT_MIN_IDLE = 2;

        private String name;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;

        public Builder setName(String name) {
            if (StringUtils.isEmpty(name)) {
                throw new IllegalArgumentException("name can not be empty");
            }

            this.name = name;
            return this;
        }

        // 每个set方法只做最基本的校验
        public Builder setMaxIdle(int maxIdle) {
            if (maxIdle < 0) {
                throw new IllegalArgumentException("maxIdle is illegal");
            }
            this.maxIdle = maxIdle;

            return this;
        }

        // 每个set方法只做最基本的校验
        public Builder setMinIdle(int minIdle) {
            if (minIdle < 0) {
                throw new IllegalArgumentException("minIdle is illegal");
            }
            this.minIdle = minIdle;
            return this;
        }

        public RedisPoolConfig build() {
            // 防止用户不set name
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("...");
            }

            // 做全局配置的校验，比如最小空闲数不能大于最大空闲数。如果没有手动set的话，就使用默认值
            if (this.minIdle > this.maxIdle) {
                throw new IllegalArgumentException("mixIdle more than minIdle");
            }

            return new RedisPoolConfig(this);
        }
    }





}
