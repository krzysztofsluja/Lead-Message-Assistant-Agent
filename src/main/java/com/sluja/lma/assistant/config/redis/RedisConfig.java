package com.sluja.lma.assistant.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.sluja.lma.assistant.operator.processes.verification.dto.VerificationProcessDTO;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, VerificationProcessDTO> verificationProcessRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, VerificationProcessDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
