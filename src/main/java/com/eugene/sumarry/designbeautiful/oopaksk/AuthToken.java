package com.eugene.sumarry.designbeautiful.oopaksk;


public class AuthToken {

    /**
     * 时间戳超时的时间
     */
    private static final Long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000L;


    private String token;

    private Long createdTime;

    /**
     * 时间戳超时的时间
     */
    private Long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;


    public AuthToken(String token, Long createdTime) {
        this.token = token;
        this.createdTime = createdTime;
    }

    public AuthToken(String token, Long createdTime, Long expiredTimeInterval) {
        this.token = token;
        this.createdTime = createdTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }



    /**
     * 根据入参url创建token
     * @param baseUrl url & query参数
     * @param createdTime 请求的创建时间，保存到token实例中
     * @return
     */
    public static AuthToken create(String baseUrl, Long createdTime) {
        // 从url中获取token参数
        String token = baseUrl.hashCode() + "";
        return new AuthToken(token, createdTime);
    }

    /**
     * 根据所有的入参 + password，按照相同的算法重新生成token信息
     * @param baseUrl url & query参数
     * @param createdTime 请求的创建时间
     * @return
     */
    public static AuthToken create(String baseUrl, Long createdTime, String password) {
        // 模拟生成token的流程
        // 组装所有的参数
        String allParams = baseUrl.toString() + createdTime;
        // 用password模拟做签名计算
        String token = allParams + password;
        return new AuthToken(token, createdTime);
    }


    /**
     * 如果当前时间 + 过期时间比请求的创建时间还大，则过期了
     * @return
     */
    public boolean isExpired() {
        return System.currentTimeMillis() + this.expiredTimeInterval > this.createdTime;
    }

    public boolean isMatch(AuthToken serverAuthToken) {
        return this.token.equals(serverAuthToken.token);
    }


}
