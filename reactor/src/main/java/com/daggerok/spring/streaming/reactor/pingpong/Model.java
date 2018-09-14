package com.daggerok.spring.streaming.reactor.pingpong;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Model implements Serializable {

  private static final long serialVersionUID = 4997822214345757899L;

  Long id;
  String body;
}
