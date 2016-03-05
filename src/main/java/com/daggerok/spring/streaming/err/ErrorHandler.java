package com.daggerok.spring.streaming.err;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;

@Log
@Controller
public class ErrorHandler implements ErrorController {
    @Value("${error.path:/error}")
    String errorPath;

    String rootPath = "/";

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void error(HttpServletRequest request, HttpServletResponse response, Throwable e) throws IOException {
        if (log.isLoggable(Level.INFO)) log.info("\nhandle an error for " + e);
        response.sendRedirect(request.getContextPath().concat(rootPath));
    }
}
