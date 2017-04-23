package com.daggerok.spring.streaming.msg.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Value
@Builder
public class Request {
  final String data;

  public String transform() {
    List<String> list = Stream.of(data.toCharArray())
        .map(String::valueOf)
        .map(String::toUpperCase)
        .collect(Collectors.toList());

    Collections.reverse(list);
    return list.stream().collect(Collectors.joining());
  }
}
