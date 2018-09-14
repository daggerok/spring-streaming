package com.daggerok.spring.streaming.reactor.pingpong;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Publisher {

  final EventBus eventBus;
  final CountDownLatch countDownLatch;

  @SneakyThrows
  public void publishModel(int times) {
    long start = System.currentTimeMillis();

    AtomicInteger counter = new AtomicInteger(1);

    IntStream.range(0, times).forEach(value ->
        eventBus.notify("models", Event.wrap(counter.getAndIncrement())));

    countDownLatch.await();

    long elapsed = System.currentTimeMillis() - start;

    log.info("Elapsed time: {} ms", elapsed);
    log.info("Average time per quote: {} ms", elapsed / times);
  }
}
