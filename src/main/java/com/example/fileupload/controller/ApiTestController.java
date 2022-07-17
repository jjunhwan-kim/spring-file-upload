package com.example.fileupload.controller;

import com.example.fileupload.exception.GitException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ApiTestController {

    @GetMapping("/test1")
    public void test() {
        throw new RuntimeException("Runtime Exception");
    }

    @GetMapping("/test2")
    public void test2() {
        throw new IllegalArgumentException("IllegalArgumentException");
    }

    @GetMapping("/test3")
    public String test3(@RequestParam Integer data) {
        return "ok";
    }

    @GetMapping("/test4")
    public String test4(@Valid RequestDto dto) {
        return "ok";
    }

    @GetMapping("/test/git")
    public void git() {
        throw new GitException("Git Exception 발생");
    }
}
