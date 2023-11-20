package com.eugene.sumarry.designbeautiful.anemicdomainmode;

import java.math.BigDecimal;

/**
 * service层的领域模型。贫血模型
 */
public class VirtualWalletBo {
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
