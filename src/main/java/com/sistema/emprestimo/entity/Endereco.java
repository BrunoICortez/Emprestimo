package com.sistema.emprestimo.entity;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotNull(message = "O campo da rua não pode ser nulo")
	private String rua;

	@NotNull(message = "O campo do numero não pode ser nulo")
	private Long numero;

	@NotNull(message = "O campo do cep não pode ser nulo")
	private String cep;

	public Endereco() {
	}

	public Endereco(String rua, Long numero, String cep) {
		
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Long getNumero() {
		return numero;
	}

}
