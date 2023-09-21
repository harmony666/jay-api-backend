package cn.ichensw.jayapiadmin;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("cn.ichensw.jayapiadmin.mapper")
@EnableScheduling // 用于启用定时任务的支持
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true) // 用于启用AspectJ切面支持
@EnableDubbo
public class JayApiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(JayApiAdminApplication.class, args);
    }

}
