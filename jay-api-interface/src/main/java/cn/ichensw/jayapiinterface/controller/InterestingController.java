package cn.ichensw.jayapiinterface.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有意思的接口
 *
 * @author jay
 */
@RestController
public class InterestingController {

    /**
     * 随机头像接口
     * @param request
     * @return
     */
    @PostMapping("/api/rand.avatar")
    public String randAvatar(HttpServletRequest request) {
        String url = "https://api.uomg.com/api/rand.avatar";
        String body = URLUtil.decode(request.getHeader("body"), CharsetUtil.CHARSET_UTF_8);
        HttpResponse httpResponse = HttpRequest.get(url + "?" + body)
                .execute();
        return httpResponse.body();
    }

    /**
     * 随机壁纸接口
     * @param request
     * @return
     */
    @PostMapping("/sjbz/api.php")
    public String randImages(HttpServletRequest request) {
        String url = "http://api.btstu.cn/sjbz/api.php";
        String body = URLUtil.decode(request.getHeader("body"), CharsetUtil.CHARSET_UTF_8);
        HttpResponse httpResponse = HttpRequest.get(url + "?" + body)
                .execute();
        return httpResponse.body();
    }

    /**
     * 随机生成毒鸡汤接口
     * @param request
     * @return
     */
    @PostMapping("/yan/api.php")
    public String poisonChicken(HttpServletRequest request) {
        String url = "http://api.btstu.cn/yan/api.php";
        String body = URLUtil.decode(request.getHeader("body"), CharsetUtil.CHARSET_UTF_8);
        HttpResponse httpResponse = HttpRequest.get(url + "?" + body)
                .execute();
        return httpResponse.body();
    }

    /**
     * 短地址生成接口
     * @param request
     * @return
     */
    @PostMapping("/api/long2dwz")
    public String long2dwz(HttpServletRequest request) {
        String url = "https://api.uomg.com/api/long2dwz";
        String body = URLUtil.decode(request.getHeader("body"), CharsetUtil.CHARSET_UTF_8);
        HttpResponse httpResponse = HttpRequest.get(url + "?" + body)
                .execute();
        return httpResponse.body();
    }

    /**
     * gpt聊天接口
     * @param text 前端传入String字符串
     * @return
     */
    @PostMapping("/v1/chat/completions")
    public String chatGpt(@RequestParam String text) {
        String url = "https://api.chatanywhere.com.cn/v1/chat/completions";
        String token = "Bearer sk-zRPWRzrLRiLTBXPZZ5mNYb92mGdh2m6CGd6WMTM4CAag1S6H";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", "gpt-3.5-turbo");
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>(){{
            put("role", "user");
            put("content", text);
        }});
        paramMap.put("messages", dataList);

        String body = HttpRequest.post(url)
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(JSONUtil.toJsonStr(paramMap))
                .execute()
                .body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        JSONArray choices = jsonObject.getJSONArray("choices");
        if (choices == null || choices.isEmpty()) {
            throw new RuntimeException("Unexpected response from chat API");
        }
        JSONObject result = choices.get(0, JSONObject.class, Boolean.TRUE);
        JSONObject message = result.getJSONObject("message");
        if (message == null) {
            throw new RuntimeException("Unexpected response from chat API");
        }
        return message.getStr("content");
    }
}

