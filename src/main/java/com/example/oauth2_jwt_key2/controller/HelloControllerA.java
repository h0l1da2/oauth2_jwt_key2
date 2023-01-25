package com.example.oauth2_jwt_key2.controller;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerA {

    /**
     * 변경 내용 테스트를 위해
     * HTTP 응답을 통해 반환한다
     */
    @GetMapping("/hello")
    public String hello(OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails details =
                (OAuth2AuthenticationDetails) authentication.getDetails();
        return "Hello"+details.getDecodedDetails();
    }
}
