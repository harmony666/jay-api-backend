package cn.ichensw.jayclientsdk.client;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static cn.ichensw.jayclientsdk.utils.SignUtils.genSign;

/**
 * API 调用
 */
public class JayApiClient {

    public static String GATEWAY_HOST = "http://localhost:8090";


    private String accessKey;

    private String secretKey;

    public JayApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public void setGatewayHost(String gatewayHost) {
        GATEWAY_HOST = gatewayHost;
    }

    /**
     * 用于构建请求头
     * @param body 请求体
     * @param method 请求方法
     * @return 返回一个构建好的请求头
     * @throws UnsupportedEncodingException
     */
    private Map<String, String> getHeaderMap(String body, String method) throws UnsupportedEncodingException {
        HashMap<String, String> map = new HashMap<>();
        map.put("accessKey", accessKey);
        map.put("nonce", RandomUtil.randomNumbers(10));
        map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        map.put("sign", genSign(body, secretKey));
        body = URLUtil.encode(body, CharsetUtil.CHARSET_UTF_8);
        map.put("body", body);
        map.put("method", method);
        return map;
    }

    /**
     * 向网关地址发送http请求，并添加请求头信息，最后返回响应的结果
     * @param params
     * @param url
     * @param method
     * @return
     * @throws UnsupportedEncodingException
     */
    public String invokeInterface(String params, String url, String method) throws UnsupportedEncodingException {
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + url)
                .header("Accept-Charset", CharsetUtil.UTF_8)
                .addHeaders(getHeaderMap(params, method))
                .body(params)
                .execute();
        return JSONUtil.formatJsonStr(httpResponse.body());
    }

}
