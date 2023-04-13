package com.sistema.emprestimo.exception;

import java.math.BigDecimal;

public class ValorEmprestimoUltrapassadoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ValorEmprestimoUltrapassadoException(BigDecimal valorInicialTotal, BigDecimal rendimentoMensalTotal) {
		super(String.format("A soma total do Valor Inicial dos emprestimos, que é %s ultrapassou o  equivalente a 10 vezes o rendimento mensal, que é %s", valorInicialTotal, rendimentoMensalTotal));
	}
	
}