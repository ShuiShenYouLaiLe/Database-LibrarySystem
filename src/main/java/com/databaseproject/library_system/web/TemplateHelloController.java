package com.databaseproject.library_system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/new/api")
public class TemplateHelloController {
    @GetMapping("/say")
    public String getTemplate() {
       return "books";
   }
    @GetMapping("/map")
    @ResponseBody
    public Map<String, String> getTemplateNoTemplate() {
       Map<String, String> map = new HashMap<>();
       map.put("1","1");
       map.put("2","2");
       return map;
    }

}
