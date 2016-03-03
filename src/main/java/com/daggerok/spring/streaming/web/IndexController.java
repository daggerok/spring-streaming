package com.daggerok.spring.streaming.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class IndexController {
    // assume that we get this settings from settingsRepository:
    private Map<String, Object> settings = new LinkedHashMap<>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("settings", settings);
        return "index";
    }
}
