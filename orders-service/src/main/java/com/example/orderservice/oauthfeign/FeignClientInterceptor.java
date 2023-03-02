package com.example.orderservice.oauthfeign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;



@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Autowired
    private OAuth2AuthorizedClientManager manager;

    @Override
    public void apply(RequestTemplate template) {

        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("rental-cars-client").principal("openid").build()).getAccessToken().getTokenValue();
        template.header("Authorization", "Bearer " + token);
    }
}