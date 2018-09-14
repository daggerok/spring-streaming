package com.daggerok.spring.streaming.reactor.pingpong;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource implements Serializable {

  private static final long serialVersionUID = 1541951122250148466L;

  String type;
  Model value;
}
