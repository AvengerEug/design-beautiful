package com.eugene.sumarry.designbeautiful.anemicdomainmode;

import java.math.BigDecimal;

/**
 * 虚拟钱包的控制器
 */
public class VirtualWalletController {

    // 通过构造函数或者IOC框架注入
    private VirtualWalletService virtualWalletService;

    //查询余额
    public BigDecimal getBalance(Long walletId) {
        return virtualWalletService.getBalance(walletId);
    }
    //出账
    public void debit(Long walletId, BigDecimal amount) {
        virtualWalletService.debit(walletId, amount);
    }
    //入账
    public void credit(Long walletId, BigDecimal amount) {
        virtualWalletService.credit(walletId, amount);
    }
    //转账
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        virtualWalletService.transfer(fromWalletId, toWalletId, amount);
    }
    //省略查询transaction的接口

}
