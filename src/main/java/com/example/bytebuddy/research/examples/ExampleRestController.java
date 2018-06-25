package com.example.bytebuddy.research.examples;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/")
public class ExampleRestController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hello,Spring Webflux Example!");
    }


}
