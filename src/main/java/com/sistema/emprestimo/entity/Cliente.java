package com.sistema.emprestimo.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente {

	@Id
	@CPF
	@NotNull(message = "O campo do CPF não pode ser nulo")
	private String cpf;

	@NotNull(message = "O campo do nome não pode ser nulo")
	private String nome;

	@NotNull(message = "O campo do telefone não pode ser nulo")
	private String telefone;

	@NotNull(message = "O campo do rendimento mensal não pode ser nulo")
	private BigDecimal rendimentoMensal;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	public Cliente() {
	}

	public Cliente(String cpf, String nome, String telefone, BigDecimal rendimentoMensal, Endereco endereco) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.rendimentoMensal = rendimentoMensal;
		this.endereco = endereco;

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public BigDecimal getRendimentoMensal() {
		return rendimentoMensal;
	}

	public void setRendimentoMensal(BigDecimal rendimentoMensal) {
		this.rendimentoMensal = rendimentoMensal;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
