package com.ct.api.errors;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleException(BusinessException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		ApiErro erro = new ApiErro();
		erro.setStatus(status);
		erro.setMenssagem(ex.getMessage());
		erro.setTempo(LocalDateTime.now());
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiErro erro = new ApiErro();
		erro.setStatus(status);
		erro.setMenssagem("Existem campos inválidos");
		erro.setTempo(LocalDateTime.now());
		return super.handleExceptionInternal(ex, erro, headers, status, request);
	}

}
