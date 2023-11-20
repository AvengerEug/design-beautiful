package com.eugene.sumarry.designbeautiful.oopaksk;

public class ApiRequest {

    private String baseUrl;

    private String token;

    private String appId;

    private Long timestamp;

    public ApiRequest(String baseUrl, String token, String appId, Long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }


    public static ApiRequest createFromFullUrl(String fullUrl) {
        String baseUrl = fullUrl.substring(0, 1);
        String token = fullUrl.substring(1, 2);
        String appId = fullUrl.substring(3, 4);
        Long timestamp = Long.valueOf(fullUrl.substring(5, 6));
        ApiRequest apiRequest = new ApiRequest(baseUrl, token, appId, timestamp);
        return apiRequest;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getToken() {
        return this.token;
    }

    public String getAppId() {
        return this.appId;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

}
