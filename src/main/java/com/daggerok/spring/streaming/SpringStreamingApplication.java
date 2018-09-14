package com.daggerok.spring.streaming;

import com.daggerok.spring.streaming.admin.AdminAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

@EnableCaching
@Import({
    AdminAutoConfiguration.class,
})
@SpringBootApplication
public class SpringStreamingApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringStreamingApplication.class, args);
  }
}
