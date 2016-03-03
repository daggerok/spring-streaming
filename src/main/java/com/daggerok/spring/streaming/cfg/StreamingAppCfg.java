package com.daggerok.spring.streaming.cfg;

import com.daggerok.spring.streaming.SpringStreamingApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@ComponentScan(basePackageClasses = SpringStreamingApplication.class)
public class StreamingAppCfg {
    @Bean
    @Primary
    public TaskScheduler scheduler() {
        return new ThreadPoolTaskScheduler();
    }
}
