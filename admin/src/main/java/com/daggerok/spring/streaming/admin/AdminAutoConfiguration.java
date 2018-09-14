package com.daggerok.spring.streaming.admin;

import org.h2.server.web.WebServlet;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdminAutoConfiguration {

  public static void main(String[] args) {
    // stub
  }

  @Bean
  public ServletRegistrationBean h2servletRegistration() {
    final ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
    registration.addUrlMappings("/console/*");
    return registration;
  }
}
