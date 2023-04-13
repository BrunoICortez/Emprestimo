package com.sistema.emprestimo.tipos;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Status {
	Bronze(1) {

		@Override
		public BigDecimal calculaValorFinal(BigDecimal valorEmprestimoInicial, BigDecimal valorEmprestimoTotal) {
			BigDecimal mult = new BigDecimal(1.8);
			BigDecimal valorEmprestimoFinal = mult.multiply(valorEmprestimoInicial, MathContext.DECIMAL32);
			return valorEmprestimoFinal;
		}
	},

	Prata(2) {
		@Override
		public BigDecimal calculaValorFinal(BigDecimal valorEmprestimoInicial, BigDecimal valorEmprestimoTotal) {

			if (valorEmprestimoInicial.compareTo(new BigDecimal("5000")) == 1) {
				BigDecimal mult = new BigDecimal(1.40);
				BigDecimal valorEmprestimoFinal = mult.multiply(valorEmprestimoInicial, MathContext.DECIMAL32);
				return valorEmprestimoFinal;
			}

			BigDecimal mult = new BigDecimal(1.60);
			BigDecimal valorEmprestimoFinal = mult.multiply(valorEmprestimoInicial, MathContext.DECIMAL32);
			return valorEmprestimoFinal;
		}
	},

	Ouro(3) {
		@Override
		public BigDecimal calculaValorFinal(BigDecimal valorEmprestimoInicial, BigDecimal valorEmprestimoTotal) {
			if (valorEmprestimoInicial.compareTo(new BigDecimal("5000")) == 1) {
				BigDecimal mult = new BigDecimal(1.40);
				BigDecimal valorEmprestimoFinal = mult.multiply(valorEmprestimoInicial, MathContext.DECIMAL32);
				return valorEmprestimoFinal;
			}

			BigDecimal mult = new BigDecimal(1.60);
			BigDecimal valorEmprestimoFinal = mult.multiply(valorEmprestimoInicial, MathContext.DECIMAL32);
			return valorEmprestimoFinal;

		}
	};

	private int codigo;

	private Status(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public abstract BigDecimal calculaValorFinal(BigDecimal valorInicial, BigDecimal valorEmprestimoTotal);
}