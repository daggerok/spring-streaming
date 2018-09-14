package com.daggerok.spring.streaming.reactor;

import com.daggerok.spring.streaming.reactor.cgf.ReactorCfg;
import com.daggerok.spring.streaming.reactor.pingpong.Publisher;
import com.daggerok.spring.streaming.reactor.pingpong.Receiver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import reactor.bus.EventBus;

import java.util.concurrent.TimeUnit;

import static java.util.Collections.singletonMap;
import static reactor.bus.selector.Selectors.$;

@Slf4j
@SpringBootApplication
@Import(ReactorCfg.class)
public class ReactorApplication {

  @SneakyThrows
  public static void main(String[] args) {

    new Thread(() -> {
      new SpringApplicationBuilder(ReactorApplication.class)
          .properties(singletonMap("server.port", 0))
          .build()
          .run(args);
    }).start();

    TimeUnit.SECONDS.sleep(10);
    System.exit(0);
  }

  @Bean
  public CommandLineRunner runner(EventBus eventBus, Receiver receiver, Publisher publisher) {
    return args -> {
      eventBus.on($("models"), receiver);
      publisher.publishModel(10);
    };
  }
}
