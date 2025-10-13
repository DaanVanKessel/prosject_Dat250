package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.UnifiedJedis;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.rmi.server.RemoteRef;

@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);

        UnifiedJedis jedis = new UnifiedJedis("redis://localhost:6379");

        jedis.hset("poll:id:votes", "optionsid", String.valueOf(10));
        jedis.hgetAll("poll:id:votes");
        jedis.hincrBy("poll:id:votes", "optionsid", 1);

        jedis.close();
    }





}
