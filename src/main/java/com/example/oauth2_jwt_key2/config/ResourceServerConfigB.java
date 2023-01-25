package com.example.oauth2_jwt_key2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 이제 공개 키는 권한 부여 서버에서 얻기 때문에
 * 리소스 서버에 있는 키 정보는 삭제함
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfigB extends ResourceServerConfigurerAdapter {
    
}
