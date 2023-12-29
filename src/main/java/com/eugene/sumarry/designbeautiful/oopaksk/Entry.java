package com.eugene.sumarry.designbeautiful.oopaksk;

/**
 * 入口
 * @author muyang
 * @create 2023/12/29 15:25
 */
public class Entry {

    public static void main(String[] args) {
        ApiAuthenticator apiAuthenticator = new DefaultApiAuthenticator(new CredentialStorage());

        // 触发认证流程
        apiAuthenticator.auth("");

    }

}
