package cn.ichensw.jayclientsdk;

import cn.ichensw.jayclientsdk.client.JayApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Jay API 客户端配置类
 * 这个类的作用就是将API客户端的相关信息从属性文件中读取出来，并将其注入到JayApiClient对象中，从而创建一个可以用于调用API的客户端
 * 这样的话，别的开发人员可以直接注入JayApiClient对象来使用这个API客户端。
 * @author jay
 */
@Data
@Configuration // 表示这是一个配置类
@ConfigurationProperties("jay.client") // 将属性文件中的属性值映射到Java对象的属性上 jay.client是属性的前缀 对应yml文件中的属性
@ComponentScan // 自动扫描当前包及其子包中的组件
public class JayApiClientConfig {

    private String accessKey;

    private String secretKey;

    /**
     * 此处方法取名无所谓的，不影响任何地方
     *
     * @return
     */
    @Bean // 这个注解表示会将JayApiClient对象添加到Spring容器中，并且可以被其他组件使用
    public JayApiClient getApiClient() {
        return new JayApiClient(accessKey, secretKey);
    }
}
