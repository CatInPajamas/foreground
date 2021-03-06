package com.crowd.foreground.config;

import org.springframework.context.annotation.Configuration;

/**
 * 支付宝接入的配置信息
 */
@Configuration
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016102400749608";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCbFvk5YTqlEsjHg8wRmP4uJAYi39+vkWOfhdXvffLsjveuZ1G9vcdrQ1O5lC2UatDXszIL8hbzKF6RYBDuSCOyZsILLeF7m/opaKihux0kTMd3FVhsQMEE6m7jui0mhrp+CC9V4PQMMac6kj7ssjUI/ehu0pwvRPBMpc3yIe0PV3C47H3mHXZ+tpA1D0htFVjcTVTLOGBWIqC6lgQ/tQfMEk4Hj+v4V4Y+2B+Z8xhkBP+jnPyux7lKE533PflFl6u49ZbRD6edJHyLFIawNVgfeYtN1PPfTxDqa1JrEbPqUvJsOjVaycUCHwPiN5HJlpfixvaqtRvkLa2eDn1qK/ZFAgMBAAECggEBAJCDAKmp4qZscXAPbDi9kaNW5+zZJKqI1AaSA/3MI26v7TSyi2U9EDPvoXVcEHiqa32COVEOPLpI1I2rsTsHN1QGEiuujdvLRHgPUIqbOxVXAcoBtsnwMKsu8zL5iRbMFbxYThs8qMsv/T5DhaSyjV6akVmqn+Qf3bXeDZ/gYu+WO8u6kY2+A3coVrLPVxPBg4rKQsdubWzSHqIOtCiN3lV48DZvE1u/2T2TLw+Qkf2HRe2vB7g8h55S9pKczprQqwhz3PWUtn6gabbwrEJfr7y1DhhVfbSaVTy1MfO89GwdyJ6u1puAfbTeeCIRP5Y72PiQ8frw9Q4273QmFIEMAUECgYEA0nHhnlEOQ2kcpsgkGNHGQft9zy7sQJkLwwjZRwJPPD/7kwZotO9dqzRKewXYLcRAkn041ZJkKJkrvsCcHnDzOIj+ceD3Tj/S99YC885hU26EIfU8u/F77BA8W2nhFCXMbelCpkYIFZEfqb8EFKdiaV8D3enwHc24MfJ8u00npzECgYEAvKmBYQ8FvtYVRQfuMTMD1d6nC22q5mOxwA85aF1609IvHPfxH/3h/oAlA2wK6bFqPUwQ4hDL8xORqEPRrXTtRIUswFieWHhdyYto8v+xbKzQQfJp2n6lPsFUrbbSehN4dVexrvko0rZdGsaNwlSzQ84CT7/3AjmBSy373/CR41UCgYADNGTBKm7TK/iZuZaDIj6oppHF4+sJGMuLDslwQWK/giHsnT5e8v40KzmvIemm2MQ69tk0t2P7EkoOP5ShCgtKIxvs1AYwFNy7lXC6Ebx9rsMoWTmxiv3eHGku4y/gRKsWSWUQbySUX39h8Fy5i8r928qNSJC1g3p4mD66A3TFIQKBgDr+UguUk/pZnSsJ0MynpCJjTh6/uznxoKpq2EuvN3I2/i5zxX14eQuI7IoGZd8vcAYc3Aaks/Gdg+V214EC2BKeBiFbVKBbexBDvJyHoeZWn4SPldQR2AAomh771RMBNaCTvGb3WHGTPsfZoIhMzgV8zLI9V7LlYm2swl8DLctFAoGAAoMaezKAbSCHIlsyRr8RfhP+w90tUFz9CCPWgjVZ94RW0zIl5jGZlw3QstPH79iolCj4S9MfMFO2zxnXwKMJHfWeWZIxRoVsz2/o1RX2vtFzjBFMckTMF1LQvTPhfnqCqd/adgLq+AGfDG1yQblgyYwlxmutQFxxrVjxqUjHu20=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgHozrpH4SRqgDmxIEdH9vVHWXbQv8cojLxt9BhjNdh5nV18xCm4NlJoJIFfesPqETiowxjkaduiO5/qEtipQ1gNuzIwTF38LMplDqtsqP7H77n9ms54VNduuWtBzZ/EH4G6aLDmhwTjCmm9sMkFQiN+1a1lv0ii+7C3aMmCA9NrwZCQHrWE2fPkHLi+x0aUH1wewOhVFETbkKlBOW34ZROted1JYLn8PaHLMdewLjW0QvnA+65+Eww9sI8cmkWHzmgnEya264X8wPulFnSUgD0JNISXPG4Xg/M+3SeJSZ8W68t1q31bQZhgERJ+FMAEALdbBcXg13yaqzKBxU1P/ZwIDAQAB";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://47.96.128.37:10086/project/pay_step4";

    // 签名方式1
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}
