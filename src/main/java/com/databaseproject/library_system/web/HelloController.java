package com.databaseproject.library_system.web;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //Here inside the url, the name must be the same as the parameter's name
    @GetMapping("/books/{id}")
    /*Another way is getOne(@PathVariable("id") bid),
    here we don't need to make sure parameter's name
    same as that name in url
     */
    /*
    This @PathVariable notation is mainly used for Get & Delete,
    while RequestParam is used for POST
     */
    public Object getOne(@PathVariable long id) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("" + id, "" + id);
        return map;
    }

    @GetMapping("/books/{id}/{usrname}")
    public Object getOne(@PathVariable("id") long bid, @PathVariable("usrname") String un) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("" + bid, un);
        return map;
    }

    @PostMapping("/books")
    public Object post(@RequestParam("name") String name, @RequestParam("isbn") String isbn) {
        Map<String, String> book = new HashMap<>();
        book.put("name", name);
        book.put("isbn", isbn);
        return book;
    }

    @PostMapping("/books/all")
    public Object getAll(@RequestParam("page") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size) {
        Map<String, String> book1 = new HashMap<>();
        book1.put("name", "哈利波特魔法石");
        book1.put("isbn", "12324251");
        book1.put("author", "J.k. Rolling");
        Map<String, String> book2 = new HashMap<>();
        book2.put("name", "三体－－死神永生");
        book2.put("isbn", "1232425121");
        book2.put("author", "大刘");
        List<Map> contents = new ArrayList<>();
        contents.add(book1);
        contents.add(book2);
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("size", size);
        pageMap.put("content", contents);
        return pageMap;
    }
}
