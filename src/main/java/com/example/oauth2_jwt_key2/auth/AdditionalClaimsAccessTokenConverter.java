package com.example.oauth2_jwt_key2.auth;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * AccessTokenConverter 객체가 토큰을 Authentication 으로 변환할 때
 * 토큰에 들어있는 맞춤형 세부 정보도 고려하도록 수정
 */
public class AdditionalClaimsAccessTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        // 초기 authentication 객체 얻기(원래 반환하려던 authentication 객체)
        OAuth2Authentication authentication = super.extractAuthentication(map);

        // 맞춤형 세부 정보 추가
        authentication.setDetails(map);

        return authentication;
    }
}
