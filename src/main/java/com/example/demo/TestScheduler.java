package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestScheduler {
    private final RestTemplate restTemplate;
    private static Integer i = 0;

    @Bean
    SecurityFilterChain hqew(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(c -> c.anyRequest().permitAll())
                .build();
    }

    @GetMapping("test")
    public ResponseEntity<Object> testTest() {
        log.warn("Got request test");
        return ResponseEntity.ok(Map.of("q", "q1"));
    }

    @GetMapping
    public ResponseEntity<Object> test(@RequestParam String text) {
        log.info("Handle request {}", text);
        ResponseEntity<?> forEntity = restTemplate.getForEntity("http://localhost:8080/test/test", Map.class);
        if ((i += 1) % 3 == 0) {
            throw new AccessDeniedException("Acc den");
        }
        if ((i += 1) % 2 == 0) {
            throw new IllegalArgumentException();
        }
        return ResponseEntity.ok("Hello worasd");
    }


//    @Scheduled(fixedDelay = 1)
//    public void tes1t() {
//        log.warn("Worn {}", UUID.randomUUID().toString());
//        log.error("ERROR {}", UUID.randomUUID().toString());
//        log.info("INFO {}", UUID.randomUUID().toString());
//    }
}
