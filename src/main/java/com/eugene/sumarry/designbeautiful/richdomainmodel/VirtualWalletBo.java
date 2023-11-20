package com.eugene.sumarry.designbeautiful.richdomainmodel;

import java.math.BigDecimal;

/**
 * service层的领域模型。充血模型
 */
public class VirtualWalletBo {
    // 设置了final属性，不会被更改
    private final Long id;
    private final Long createTime;
    // 钱包金额，只允许当前类修改。外部类要修改，则需要使用相关的钱包api
    private BigDecimal balance;

    public VirtualWalletBo(Long id, Long createTime, BigDecimal balance) {
        this.id = id;
        this.createTime = createTime;
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    // 扣钱
    public void debit(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
        this.balance = this.balance.subtract(amount);
    }

    // 加钱
    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException();
        }
        this.balance = this.balance.add(amount);
    }
}
