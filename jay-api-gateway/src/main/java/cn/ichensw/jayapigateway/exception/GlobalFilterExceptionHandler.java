package cn.ichensw.jayapigateway.exception;

import cn.ichensw.jayapicommon.common.BaseResponse;
import cn.ichensw.jayapicommon.common.ResultUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局异常处理器，用于处理请求过程中抛出的异常
 *
 */
@Slf4j
@Component // 这个注解表示这个类是一个Spring管理的组件，可以自动地注入到其他组件中
public class GlobalFilterExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        // 检查响应是否已经提交，如果提交，则返回一个错误
        if (response.isCommitted()) {
            return Mono.error(ex);
        }
        // 设置返回JSON
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }
        return handleBusinessException(exchange, ex);
    }

    /**
     * 处理业务异常
     * @param exchange
     * @param ex
     * @return
     */
    public Mono<Void> handleBusinessException(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                response.setStatusCode(HttpStatus.FORBIDDEN);
                log.error("{}\n {}", ex.getMessage(), ex.getStackTrace());
                // 使用BaseResponse类和ResultUtils类来构造响应体
                BaseResponse<String> fail = ResultUtils.fail(ex.getMessage());
                log.error("{}",fail);
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(fail));
            } catch (JsonProcessingException e) {
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }
}
