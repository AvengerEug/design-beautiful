package com.eugene.sumarry.designbeautiful.richdomainmodel;

import java.math.BigDecimal;

/**
 * dao层的虚拟钱包实体
 */
public class VirtualWalletEntity {

    private Long id;
    private Long createTime;
    private BigDecimal balance;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
