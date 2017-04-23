package com.daggerok.spring.streaming.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketCfg extends AbstractWebSocketMessageBrokerConfigurer {
  @Autowired
  private StreamingAppCfg cfg;

  /**
   * Register STOMP endpoints mapping each to a specific URL and (optionally)
   * enabling and configuring SockJS fallback options.
   *
   * @param registry
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/messaging").withSockJS();
  }

  /*
      @Override
      public void configureMessageBroker(MessageBrokerRegistry registry) {
          registry.enableSimpleBroker().setTaskScheduler(cfg.scheduler());
      }
  */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.setApplicationDestinationPrefixes("/app")
        .enableSimpleBroker("/topic", "/queue") // replace this embedded broker with rabbotmq/activemq
        .setTaskScheduler(cfg.scheduler());
  }
}
