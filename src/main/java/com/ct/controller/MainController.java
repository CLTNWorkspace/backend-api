package com.ct.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class MainController {
	@GetMapping
	public String main() {
		return "Olá mundo!";
	}
}
