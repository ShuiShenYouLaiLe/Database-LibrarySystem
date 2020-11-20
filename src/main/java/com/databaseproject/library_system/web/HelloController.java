package com.databaseproject.library_system.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @RequestMapping("/say")
    public String hello() {
        return "hello mtf";
    }

    @RequestMapping(value = "say_get", method = RequestMethod.GET)
    public String hello_get() {
        return "hello_get";
    }

    @GetMapping("get_say")
    public String get_say() {
        return "hello_get";
    }

    @PostMapping("/post_mapping")
    public String post_sth(){
        return "post";
    }

}
