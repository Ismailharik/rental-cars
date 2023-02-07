package com.example.orderservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope //FOR geting
public class OrderConfigTestController {
//    @Value("${global.params.p1}")
//    private  String p1;
//    @Value("${global.params.p2}")
//    private String p2;
//    @Value("${order.params.x}")
//    private String x;
//    @Value("${order.params.y}")
//    private String y;
//
//    @GetMapping("/params")
//    public Map<String,String> params(){
//        return Map.of("p1",p1,"p2",p2,"x",x,"y",y);
//    }
}
