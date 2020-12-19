package com.ct.api.errors;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiErro {

	private HttpStatus status;
	private LocalDateTime tempo;
	private String menssagem;
}
