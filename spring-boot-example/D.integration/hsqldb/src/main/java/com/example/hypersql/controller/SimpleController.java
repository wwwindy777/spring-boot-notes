package com.example.hypersql.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

  @GetMapping("/")
  public String home() {
    return "hello world";
  }
}
