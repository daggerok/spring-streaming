package com.daggerok.spring.streaming.reactor.cgf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import reactor.Environment;
import reactor.bus.EventBus;

import java.util.concurrent.CountDownLatch;

@Configuration
public class ReactorCfg {
  @Bean
  public CountDownLatch countDownLatch() {
    return new CountDownLatch(3);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  Environment env() {
    return Environment.initializeIfEmpty().assignErrorJournal();
  }

  @Bean
  EventBus createEventBus(Environment env) {
    return EventBus.create(env, Environment.THREAD_POOL);
  }
}
