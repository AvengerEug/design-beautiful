package com.eugene.sumarry.designbeautiful.builderdesign.case1;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 规则：
 * 当isRef为true的时候，arg表示String类型的refBeanId，type不需要设置；
 * 当isRef为false的时候，arg、type都需要设置。
 * 请根据这个需求，完善ConstructorArg类。
 *
 * @create 2023/12/29 15:51
 */
public class ConstructorArg {

    private Object arg;
    private String type;
    private Boolean isRef;

    private ConstructorArg(Builder builder) {
        this.arg = builder.arg;
        this.type = builder.type;
        this.isRef = builder.isRef;
    }

    public static class Builder {
        private Object arg;
        private String type;
        private Boolean isRef;

        public Builder setArg(Object arg) {
            this.arg = arg;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setRef(Boolean ref) {
            if (ref == null) {
                throw new IllegalArgumentException("isRef must be not null");
            }
            this.isRef = ref;
            return this;
        }

        public ConstructorArg builder() {
            if (this.isRef == null) {
                throw new IllegalArgumentException("isRef must be not null");
            }

            if (BooleanUtils.isTrue(isRef)) {
                // 当isRef为true的时候，arg表示String类型的refBeanId，type不需要设置；
                if (this.arg == null || !(this.arg instanceof String)) {
                    throw new IllegalArgumentException("arg must be not null when isRef is true");
                }
            } else {
                // isRef为false的时候，arg、type都需要设置。
                if (this.arg == null) {
                    throw new IllegalArgumentException("arg must be not null when isRef is false");
                }
                if (StringUtils.isEmpty(this.type)) {
                    throw new IllegalArgumentException("type must be not null when isRef is false");
                }
            }


            return new ConstructorArg(this);
        }

    }

}
