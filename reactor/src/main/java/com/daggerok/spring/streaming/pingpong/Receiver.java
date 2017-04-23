package com.daggerok.spring.streaming.pingpong;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.bus.Event;
import reactor.fn.Consumer;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class Receiver implements Consumer<Event<Integer>> {
  @Autowired
  CountDownLatch countDownLatch;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  ObjectMapper objectMapper;

  /**
   * Execute the logic of the action, accepting the given parameter.
   *
   * @param integerEvent The parameter to pass to the consumer.
   */
  @Override
  @SneakyThrows
  public void accept(Event<Integer> integerEvent) {
    Resource resource = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Resource.class);

    log.info("Model {}: {}", integerEvent.getData(), objectMapper.writeValueAsString(resource));
  }
}
