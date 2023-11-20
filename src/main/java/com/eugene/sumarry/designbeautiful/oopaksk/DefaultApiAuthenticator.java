package com.eugene.sumarry.designbeautiful.oopaksk;


public class DefaultApiAuthenticator implements ApiAuthenticator {

    private CredentialStorage credentialStorage;

    @Override
    public void auth(String url) {
        // 基于url构建apiRequest对象
        ApiRequest apiRequest = ApiRequest.createFromFullUrl(url);
        this.auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        // 基于apiRequest构建出用户请求对应的authToken出来
        AuthToken authToken = AuthToken.create(apiRequest.getBaseUrl(), apiRequest.getTimestamp());

        // 判断authToken是否的timestamp是否过期
        if (authToken.isExpired()) {
            throw new RuntimeException("timestamp非法");
        }

        // 根据appId获取密码
        String password = credentialStorage.getPasswordByAppId(apiRequest.getAppId());

        // 生成服务端签名tokne
        AuthToken serverToken = AuthToken.create(apiRequest.getBaseUrl(), apiRequest.getTimestamp(), password);

        if (!authToken.isMatch(serverToken)) {
            throw new RuntimeException("鉴权不通过");
        }

        // 签名通过，do nothing

    }
}
