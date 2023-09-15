package cn.ichensw.jayclientsdk;

import cn.ichensw.jayclientsdk.client.JayApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Jay API 客户端配置类
 * @author jay
 */
@Data
@Configuration
@ConfigurationProperties("jay.client")
@ComponentScan
public class JayApiClientConfig {

    private String accessKey;

    private String secretKey;

    /**
     * 此处方法取名无所谓的，不影响任何地方
     *
     * @return
     */
    @Bean
    public JayApiClient getApiClient() {
        return new JayApiClient(accessKey, secretKey);
    }
}
