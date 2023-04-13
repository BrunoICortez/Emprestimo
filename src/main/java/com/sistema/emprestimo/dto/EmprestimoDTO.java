package com.sistema.emprestimo.dto;

import java.math.BigDecimal;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.sistema.emprestimo.entity.Cliente;
import com.sistema.emprestimo.entity.Emprestimo;
import com.sistema.emprestimo.tipos.Status;

public class EmprestimoDTO {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_cpf")
	private Cliente cpfCliente;
	
	@NotNull(message = "Valor inicial nao pode ser nulo")
	private BigDecimal valorEmprestimoInicial;

	private BigDecimal valorEmprestimoFinal;
	
	@NotNull(message = "Relacionamento nao pode ser nulo")
	private Status relacionamento;
	
	@NotNull(message = "Data inicial nao pode ser nulo")
	private String dataInicial;
	
	@NotNull(message = "Data final nao pode ser nulo")
	private String dataFinal;

	public EmprestimoDTO() {

	}

	public EmprestimoDTO(Long id, BigDecimal valorEmprestimoInicial, BigDecimal valorEmprestimoFinal,
			Status relacionamento, String dataInicial, String dataFinal) {
	
		this.id = id;
		this.valorEmprestimoInicial = valorEmprestimoInicial;
		this.valorEmprestimoFinal = valorEmprestimoFinal;
		this.relacionamento = relacionamento;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public static EmprestimoDTO retornaEmprestimo(Emprestimo emprestimo) {
		EmprestimoDTO emprestimoDTO = new EmprestimoDTO(emprestimo.getId(), emprestimo.getvalorEmprestimoInicial(),
				emprestimo.getvalorEmprestimoFinal(), emprestimo.getRelacionamento(), emprestimo.getDataInicial(),
				emprestimo.getDataFinal());
		return emprestimoDTO;
	}

	public static Emprestimo retornaEmprestimo(EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = new Emprestimo(emprestimoDTO.getId(), emprestimoDTO.getValorEmprestimoInicial(),
				emprestimoDTO.getValorEmprestimoFinal(), emprestimoDTO.getRelacionamento(),
				emprestimoDTO.getDataInicial(), emprestimoDTO.getDataFinal());
		return emprestimo;
	}

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return cpfCliente;
	}

	public void setCliente(Cliente cliente) {
		this.cpfCliente = cliente;
	}

	public BigDecimal getValorEmprestimoInicial() {
		return valorEmprestimoInicial;
	}

	public void setValorInicial(BigDecimal valorEmprestimoInicial) {
		this.valorEmprestimoInicial = valorEmprestimoInicial;
	}

	public BigDecimal getValorEmprestimoFinal() {
		return valorEmprestimoFinal;
	}

	public void setValorEmprestimoFinal(BigDecimal valorEmprestimoFinal) {
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
