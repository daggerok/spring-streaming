package com.daggerok.spring.streaming.sse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/test")
public class SseEmitterController {
    private static Logger log = LoggerFactory.getLogger(SseEmitterController.class);

    private final Set<SseEmitter> emitters = new CopyOnWriteArraySet<>();

    @Autowired
    public SseEmitterController(TaskScheduler scheduler) {
        scheduleTasks(scheduler, emitters);
    }

    private void scheduleTasks(TaskScheduler scheduler, Set<SseEmitter> emitters) {
        AtomicInteger integer = new AtomicInteger();
        scheduler.scheduleWithFixedDelay(() -> {
            int i = integer.getAndIncrement();
            emitters.forEach(emitter -> {
                try {
                    log.warn("sending value to: {}", emitter);
                    emitter.send(i);
                } catch (IOException e) {
                    log.warn("exception for: {}", emitter);
                    emitters.remove(emitter);
                }
            });
        }, 1000);
    }

    @RequestMapping(path = "/sse-interval", method = RequestMethod.GET)
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

    @RequestMapping(path = "/sse-interval-with-status", method = RequestMethod.GET)
    public ResponseEntity<SseEmitter> handleWithResponseEntry(@RequestParam(required = false) String q) {
        return StringUtils.hasText(q) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null) :
                ResponseEntity.ok().body(initEmitter());
    }
}
