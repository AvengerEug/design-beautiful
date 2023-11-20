package com.eugene.sumarry.designbeautiful.richdomainmodel;

import java.math.BigDecimal;

/**
 * 虚拟钱包的service层
 */
public class VirtualWalletService {

    // 通过构造函数或者IOC框架注入
    private VirtualWalletRepository walletRepo;
    private VirtualWalletTransactionRepository transactionRepo;

    /**
     * 将entity转化成bi
     * @param walletEntity
     * @return
     */
    private VirtualWalletBo convert(VirtualWalletEntity walletEntity) {
        if (walletEntity == null) {
            return null;
        }

        VirtualWalletBo virtualWalletBo = new VirtualWalletBo(walletEntity.getId(), walletEntity.getCreateTime(), walletEntity.getBalance());
        return virtualWalletBo;
    }

    public VirtualWalletBo getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWalletBo walletBo = convert(walletEntity);
        return walletBo;
    }

    public BigDecimal getBalance(Long walletId) {
        return walletRepo.getBalance(walletId);
    }

//    @Transactional
    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWalletBo virtualWalletBo = convert(walletEntity);
        if (walletEntity == null) {
            throw new NullPointerException();
        }

        // 这段是核心，利用了封装的特性做了金额的减法操作，最终减法的合理性判断和剩余金额都在virtualWalletBo中提现了，因此后续的更新操作，直接用virtualWallet中取数据即可
        virtualWalletBo.debit(amount);

        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionTypeEnum.DEBIT);
        transactionEntity.setFromWalletId(walletId);
        transactionRepo.saveTransaction(transactionEntity);

        // 这段代码是核心：我们直接从virtualWalletBo取数据更新到db中
        walletRepo.updateBalance(walletId, virtualWalletBo.getBalance());
    }

//    @Transactional
    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWalletBo virtualWalletBo = convert(walletEntity);
        if (walletEntity == null) {
            throw new NullPointerException();
        }

        // 这段是核心，利用了封装的特性做了金额的加法操作，最终加法的合理性判断和剩余金额都在virtualWalletBo中提现了，因此后续的更新操作，直接用virtualWallet中取数据即可
        virtualWalletBo.credit(amount);

        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionTypeEnum.CREDIT);
        transactionEntity.setFromWalletId(walletId);
        transactionRepo.saveTransaction(transactionEntity);

        // 这段代码是核心：我们直接从virtualWalletBo取数据更新到db中
        walletRepo.updateBalance(walletId, virtualWalletBo.getBalance());
    }
//    @Transactional
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionTypeEnum.TRANSFER);
        transactionEntity.setFromWalletId(fromWalletId);
        transactionEntity.setToWalletId(toWalletId);
        transactionRepo.saveTransaction(transactionEntity);
        debit(fromWalletId, amount);
        credit(toWalletId, amount);
    }

}
