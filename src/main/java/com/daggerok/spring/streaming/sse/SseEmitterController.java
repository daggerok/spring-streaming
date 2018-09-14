package com.daggerok.spring.streaming.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Controller
@RequestMapping("/test")
public class SseEmitterController {

  private final Set<SseEmitter> emitters = new CopyOnWriteArraySet<>();
  private final TaskScheduler scheduler;

  @Autowired
  public SseEmitterController(TaskScheduler scheduler) {
    this.scheduler = scheduler;
    scheduleTasks(scheduler, emitters);
  }

  private void scheduleTasks(TaskScheduler scheduler, Set<SseEmitter> emitters) {
    AtomicInteger integer = new AtomicInteger();
    scheduler.scheduleWithFixedDelay(() -> {
      int i = integer.getAndIncrement();
      emitters.forEach(emitter -> {
        try {
          log.warn("\nsending value to: {}", i);
          emitter.send(i);
        } catch (IOException e) {
          log.warn("\nexception: {}", e.getMessage());
          emitters.remove(emitter);
        }
      });
    }, 1000);
  }

  @GetMapping("/sse-interval")
  public SseEmitter handle() {
    return initEmitter();
  }

  private SseEmitter initEmitter() {
    SseEmitter emitter = new SseEmitter();
    emitter.onCompletion(() -> {
      log.warn("interval stream complete for: {}", emitter);
      emitters.remove(emitter); // this will happen later
    });
    emitters.add(emitter);
    return emitter;
  }

  @GetMapping("/sse-interval-with-status")
  public ResponseEntity<SseEmitter> handleWithResponseEntry(@RequestParam(required = false) String q) {
    return StringUtils.hasText(q) ?
        ResponseEntity.status(HttpStatus.NO_CONTENT).body(null) :
        ResponseEntity.ok().body(initEmitter());
  }

  @GetMapping("/tic-tac-toe")
  public SseEmitter ticTacToe() throws IOException {
    SseEmitter emitter = new SseEmitter();
    new Thread(() -> {
      try {
        emitter.send("{tic: " + LocalDateTime.now() + "}", MediaType.APPLICATION_JSON);
        TimeUnit.MILLISECONDS.sleep(100);
        emitter.send("{tac: " + LocalDateTime.now() + "}", MediaType.APPLICATION_JSON);
        TimeUnit.MILLISECONDS.sleep(100);
        emitter.send("{toe: 'thank you!'}", MediaType.APPLICATION_JSON);
        emitter.complete();
      } catch (Exception e) {
      }
    }).start();
    return emitter;
  }

  @ResponseBody
  @GetMapping("/tic-tac")
  public String ticTac() throws IOException, InterruptedException {
    TimeUnit.MILLISECONDS.sleep(50);
    return "data:{message: 'thanks!'}\n";
  }

  @GetMapping
  public String index() {
    return "/index.html";
  }
}
