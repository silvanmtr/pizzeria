package br.com.pizzeria.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.pizzeria.service.exception.TamanhoJaCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(TamanhoJaCadastradoException.class)
	public ResponseEntity<String> handleNomeEstiloJaCadastradoException(TamanhoJaCadastradoException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
