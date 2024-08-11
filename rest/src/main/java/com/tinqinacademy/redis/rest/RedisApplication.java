package com.tinqinacademy.redis.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;

@SpringBootApplication(scanBasePackages = {"com.tinqinacademy.redis"})
@EntityScan(basePackages = {"com.tinqinacademy.redis.persistence.models"})
//@EnableJpaRepositories(basePackages = {"com.tinqinacademy.authentication.persistence.repositories"})
@EnableRedisIndexedHttpSession(maxInactiveIntervalInSeconds = 300)
public class RedisApplication {

    public static void main(String[] args) {

        SpringApplication.run(RedisApplication.class, args);
    }
}