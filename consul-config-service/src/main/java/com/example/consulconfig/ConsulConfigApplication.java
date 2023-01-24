package com.example.consulconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

@SpringBootApplication
public class ConsulConfigApplication {

    @Autowired
    private VaultTemplate vaultTemplate;
    public static void main(String[] args) {
        SpringApplication.run(ConsulConfigApplication.class, args);
    }

    @Bean
    CommandLineRunner start(){
        return args -> {
            vaultTemplate.opsForVersionedKeyValue("secret").put("keyPair", Map.of("privKey","1234","pubKey","4321"));
        };
    }
}
