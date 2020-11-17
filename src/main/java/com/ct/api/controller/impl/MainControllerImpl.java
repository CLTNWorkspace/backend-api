package com.ct.api.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.ct.api.controller.MainController;

@RestController
public class MainControllerImpl implements MainController {

	@Override
	public String main() {
		return "teste";
	}

}
