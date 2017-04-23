package com.daggerok.spring.streaming;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringStreamingApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringStreamingApplicationTests {

  @Test
  public void contextLoads() {}
}
