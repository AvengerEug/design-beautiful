package com.eugene.sumarry.designbeautiful.anemicdomainmode;

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
        VirtualWalletBo virtualWalletBo = new VirtualWalletBo();
        virtualWalletBo.setBalance(walletEntity.getBalance());
        virtualWalletBo.setCreateTime(walletEntity.getCreateTime());
        virtualWalletBo.setId(walletEntity.getId());

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
        BigDecimal balance = walletEntity.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new NoSufficientBalanceException();
        }
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionTypeEnum.DEBIT);
        transactionEntity.setFromWalletId(walletId);
        transactionRepo.saveTransaction(transactionEntity);
        // 这段代码是核心：我们把entity对象取出来后，没有在service类中做了一些逻辑处理。这里并没有用到Bo对象，就是在代码中做了一个减法操作
        walletRepo.updateBalance(walletId, balance.subtract(amount));
    }

//    @Transactional
    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionTypeEnum.CREDIT);
        transactionEntity.setFromWalletId(walletId);
        transactionRepo.saveTransaction(transactionEntity);
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        // 这段代码是核心：我们把entity对象取出来后，没有在service类中做了一些逻辑处理。这里并没有用到Bo对象，就是在代码中做了一个加法操作
        walletRepo.updateBalance(walletId, balance.add(amount));
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
