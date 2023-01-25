package com.example.oauth2_jwt_key2.config;

import com.example.oauth2_jwt_key2.auth.AdditionalClaimsAccessTokenConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * OAuth 없이 비대칭 키를 이용하자!
 */
@Configuration
public class ResourceServerConfigC extends WebSecurityConfigurerAdapter {

    @Value("${publicKey}")
    private String publicKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // jwt 토큰 서명 검증에 jwtDecoder() 이용!
        http.oauth2ResourceServer(
                c -> c.jwt(
                        j -> j.decoder(jwtDecoder())
                )
        );

        http.authorizeRequests()
                .anyRequest().authenticated();
    }

    // 공개 키로 토큰을 검증하는 메서드
    @Bean
    public JwtDecoder jwtDecoder() {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] key = Base64.getDecoder().decode(publicKey);

            X509EncodedKeySpec x509 = new X509EncodedKeySpec(key);
            RSAPublicKey rsaKey = (RSAPublicKey) keyFactory.generatePublic(x509);
            return NimbusJwtDecoder.withPublicKey(rsaKey).build();
        } catch (Exception e) {
            throw new RuntimeException("Wrong public Key");
        }
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
    AdditionalClaimsAccessTokenConverter converter = new AdditionalClaimsAccessTokenConverter();

        converter.setVerifierKey(publicKey);

        return converter;
    }
}
