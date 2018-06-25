package com.example.bytebuddy.research;

import java.util.concurrent.Callable;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class ControllerInterceptor {

    public static final Logger LOG =LoggerFactory.getLogger(ControllerInterceptor.class);
    @RuntimeType
    public static Mono<HandlerResult> intercept(@SuperCall Callable<Mono<HandlerResult>> callable, @AllArguments Object[] args) {
        StopWatch stopWatch = new StopWatch();
        try {
            ServerWebExchange serverWebExchange = ServerWebExchange.class.cast(args[0]);
            ServerHttpRequest request = serverWebExchange.getRequest();

            stopWatch.start();
            Mono<HandlerResult> mono = callable.call();
            stopWatch.stop();
            LOG.info("Agent controller: request method:{},request url:{}, cost time:{} ms",request.getMethod().name(),request.getURI(),stopWatch.getTotalTimeMillis());
            return mono;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
