package com.sistema.emprestimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmprestimoIdNaoEncontradoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public EmprestimoIdNaoEncontradoException(Long Id) {
		super(String.format("O Id %s nao foi encontrado", Id));
	}
	
}
