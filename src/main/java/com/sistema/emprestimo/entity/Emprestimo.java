package com.sistema.emprestimo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.sistema.emprestimo.tipos.Status;

@Entity
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_cpf")
	private Cliente cpfCliente;

	@NotNull(message = "O campo do valor inicial não pode ser nulo")
	private BigDecimal valorEmprestimoInicial;

	private BigDecimal valorEmprestimoFinal;

	@NotNull(message = "O campo do relacionamento não pode ser nulo")
	private Status relacionamento;

	@NotNull(message = "O campo do data inicial não pode ser nulo")
	private String dataInicial;

	@NotNull(message = "O campo do data final não pode ser nulo")
	private String dataFinal;

	public Emprestimo() {

	}

	public Emprestimo(Long id, BigDecimal valorEmprestimoInicial, BigDecimal valorEmprestimoFinal,Status relacionamento, String dataInicial, String dataFinal) {
		
		this.id = id;
		this.valorEmprestimoInicial = valorEmprestimoInicial;
		this.valorEmprestimoFinal = valorEmprestimoFinal;
		this.relacionamento = relacionamento;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cpfCliente;
	}

	public void setCliente(Cliente cliente) {
		this.cpfCliente = cliente;
	}

	public BigDecimal getvalorEmprestimoInicial() {
		return valorEmprestimoInicial;
	}

	public void setvalorEmprestimoInicial(BigDecimal valorEmprestimoInicial) {
		this.valorEmprestimoInicial = valorEmprestimoInicial;
	}

	public BigDecimal getvalorEmprestimoFinal() {
		return valorEmprestimoFinal;
	}

	public void setvalorEmprestimoFinal(BigDecimal valorEmprestimoFinal) {
		this.valorEmprestimoFinal = valorEmprestimoFinal;
	}

	public Status getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(Status relacionamento) {
		this.relacionamento = relacionamento;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

}
