package com.daggerok.spring.streaming.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcCfg extends WebMvcConfigurerAdapter {
  /**
   * {@inheritDoc}
   * <p>This implementation is empty.
   *
   * @param configurer
   */
  @Override
  public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    configurer.setDefaultTimeout(-1);
  }
}
