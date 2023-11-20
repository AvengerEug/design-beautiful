package com.eugene.sumarry.designbeautiful.oopaksk;

public class CredentialStorage {

    /**
     * 模拟appId获取password的逻辑
     * @param appId
     * @return
     */
    public String getPasswordByAppId(String appId) {
        return appId.hashCode() + "";
    }

}
