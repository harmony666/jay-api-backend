package cn.ichensw.jayapiinterface.controller;

import cn.ichensw.jayclientsdk.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jay
 */
@RestController
public class NameController {

    /**
     * 获取用户名接口
     * @param user
     * @return
     */
    @PostMapping("/api/name/user")
    public String getUserNameByPost(@RequestBody User user) {
        return "POST 你的用户名字是：" + user.getUsername();
    }

}
