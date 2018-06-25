package com.example.bytebuddy.research.examples;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.ipc.netty.http.server.HttpServer;

@Configuration
public class ExampleRouter {

    @Bean
    /**
     * Route for matching the basic route example
     */
    public RouterFunction<ServerResponse> routeExample(ExampleHandler exampleHandler) {

        return RouterFunctions
                .route(RequestPredicates.GET("/example")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        exampleHandler::hello);
    }

    @Bean
    /**
     * Route for matching the 'one step further' example
     */
    public RouterFunction<ServerResponse> routeExampleOneStepFurther(
            ExampleHandler exampleHandler) {

        return RouterFunctions
                .route(RequestPredicates.GET("/exampleFurther1")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        exampleHandler::helloFurther1)
                .andRoute(RequestPredicates.GET("/exampleFurther2")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        exampleHandler::helloFurther2)
                .andRoute(RequestPredicates.GET("/exampleFurther3")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        exampleHandler::helloFurther3);
    }

    @Bean
    public HttpServer httpServer(RouterFunction<?> routeExample) {
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routeExample);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer server = HttpServer.create("localhost", Integer.valueOf("8081"));
        server.newHandler(adapter);

        return server;
    }

}
