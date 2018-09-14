package com.daggerok.spring.streaming;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    classes = SpringStreamingApplication.class)
public class SpringStreamingApplicationTests {

  @Test
  public void contextLoads() {}
}
