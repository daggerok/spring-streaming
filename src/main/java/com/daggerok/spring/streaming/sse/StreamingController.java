package com.daggerok.spring.streaming.sse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Controller
public class StreamingController {
    private static Logger log = LoggerFactory.getLogger(StreamingController.class);

    @RequestMapping(value = "/streaming-response-body", method = RequestMethod.GET)
    public StreamingResponseBody handle() {
        return outputStream -> {
            for (int value = 0; value < 10; value++) {
                try {
                    outputStream.write(String.format("%s\n", value).getBytes(Charset.forName("UTF-8")));
                    outputStream.flush();
                    log.warn("wrote: {}", value);
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    log.warn("streaming exception occurs for: {}", value);
                }
            }
        };
    }
}
