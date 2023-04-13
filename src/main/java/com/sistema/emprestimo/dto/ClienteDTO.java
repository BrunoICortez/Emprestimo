package com.sistema.emprestimo.dto;

import java.math.BigDecimal;

import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

import com.sistema.emprestimo.entity.Cliente;
import com.sistema.emprestimo.entity.Endereco;

public class ClienteDTO {

	@Id
	@CPF(message = "CPF Inválido")
	private String cpf;
	private String nome;
	private String telefone;
	private BigDecimal rendimentoMensal;
	private Endereco endereco;
	

	public ClienteDTO() {
	}

	public ClienteDTO(String cpf, String nome, String telefone, BigDecimal rendimentoMensal, Endereco endereco) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;		
		this.rendimentoMensal = rendimentoMensal;
		this.endereco = endereco;
	}

	public static ClienteDTO retornaCliente(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO(cliente.getCpf(), cliente.getNome(), cliente.getTelefone(),cliente.getRendimentoMensal(),
				cliente.getEndereco());
		return clienteDTO;
	}

	public static Cliente retornaCliente(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente(clienteDTO.getCpf(), clienteDTO.getNome(), clienteDTO.getTelefone(),clienteDTO.getRendimentoMensal(),
				clienteDTO.getEndereco());
		return cliente;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getRendimentoMensal() {
		return rendimentoMensal;
	}

	public void setRendimentoMensal(BigDecimal rendimentoMensal) {
		this.rendimentoMensal = rendimentoMensal;
	}

}
