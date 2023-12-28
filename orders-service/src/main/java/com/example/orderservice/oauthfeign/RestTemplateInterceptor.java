//package com.example.orderservice.oauthfeign;
//
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpRequest;
//import org.springframework.http.client.ClientHttpRequestExecution;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//
//import java.io.IOException;
//
//public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
//
//    private OAuth2AuthorizedClientManager manager;
//
//    private Logger logger= LoggerFactory.getLogger(RestTemplateInterceptor.class);
//
//    public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
//        this.manager = manager;
//    }
//
//    @Override
//    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("rental-cars-client").principal("openid").build()).getAccessToken().getTokenValue();
//
//        logger.info("Rest Template interceptor: Token :  {} ",token);
//
//        request.getHeaders().add("Authorization","Bearer "+token);
//        return execution.execute(request,body);
//    }
//}