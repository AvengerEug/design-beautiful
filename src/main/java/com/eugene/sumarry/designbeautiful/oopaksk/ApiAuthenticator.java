package com.eugene.sumarry.designbeautiful.oopaksk;

/**
 * api鉴权类(接口)。
 *   鉴权是一个行为，我们需要定义成接口给外部调用者使用。需要封装所有的实现细节
 */
public interface ApiAuthenticator {


    void auth(String url);

    void auth(ApiRequest apiRequest);

}
