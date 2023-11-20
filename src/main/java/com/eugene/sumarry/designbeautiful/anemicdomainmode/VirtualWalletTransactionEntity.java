package com.eugene.sumarry.designbeautiful.anemicdomainmode;

import java.math.BigDecimal;

/**
 * dao层交易记录的领域模型
 */
public class VirtualWalletTransactionEntity {

    private BigDecimal amount;

    private Long createTime;

    private TransactionTypeEnum type;

    private Long fromWalletId;

    private Long toWalletId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public TransactionTypeEnum getType() {
        return type;
    }

    public void setType(TransactionTypeEnum type) {
        this.type = type;
    }

    public Long getFromWalletId() {
        return fromWalletId;
    }

    public void setFromWalletId(Long fromWalletId) {
        this.fromWalletId = fromWalletId;
    }

    public Long getToWalletId() {
        return toWalletId;
    }

    public void setToWalletId(Long toWalletId) {
        this.toWalletId = toWalletId;
    }
}
