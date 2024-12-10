package com.example.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // '/api/'로 시작하는 모든 요청에 대해 CORS 허용
                .allowedOrigins("http://localhost:3000") // 프론트엔드 도메인 (localhost:3000) 허용
                .allowedMethods("GET", "POST") // 허용할 HTTP 메소드 설정
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 자격 증명 포함 가능
    }
}
