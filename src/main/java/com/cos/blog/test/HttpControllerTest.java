package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    @GetMapping("/http/get")
    public String getTest(){
        return "get요청";
    }
    @PostMapping("/http/get")
    public String postTest(){
        return "post요청";
    }
    @PutMapping("/http/put")
    public String putTest(){
        return "put요청";
    }
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete요청";
    }
}
