package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello, world!";
	}
}
