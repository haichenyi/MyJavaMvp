package com.haichenyi.myproject.model.http;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public interface ProtocolHttp {
    String HTTP_HOST = "http://192.168.23.2/";

    /**
     * Retrofit在传接口的时候，如果传的是一个完整的链接，在拦截器里面
     * 获取的url，就不会拼接前面host，如果传的不是一个完整的链接，则会拼接
     */
    String HTTP_COMMON = "http://ip.taobao.com/service/";
    String METHOD_LOGIN_CODE = HTTP_COMMON + "getIpInfo.php";//登录发送验证码
}
