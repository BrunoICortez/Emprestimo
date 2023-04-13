package com.sistema.emprestimo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.emprestimo.dto.EmprestimoDTO;
import com.sistema.emprestimo.entity.Emprestimo;
import com.sistema.emprestimo.exception.ClienteNaoEncontradoException;
import com.sistema.emprestimo.exception.EmprestimoIdNaoEncontradoException;
import com.sistema.emprestimo.exception.ValorEmprestimoUltrapassadoException;
import com.sistema.emprestimo.service.EmprestimoService;
import com.sistema.emprestimo.service.MensagemSucesso;

@RestController
@RequestMapping("/clientes")
public class EmprestimoController {
	
	private EmprestimoService emprestimoService;
	
	public EmprestimoController(EmprestimoService emprestimoService) {
		
		this.emprestimoService = emprestimoService;
		
	}
	
	@PostMapping("/{clienteCpf}/emprestimos")
	@ResponseStatus(HttpStatus.CREATED)
	public Emprestimo cadastrarEmprestimo(@PathVariable String clienteCpf, @Valid @RequestBody Emprestimo emprestimo)	throws ClienteNaoEncontradoException, ValorEmprestimoUltrapassadoException {
		return this.emprestimoService.cadastrarEmprestimo(emprestimo, clienteCpf);
	}

	@GetMapping("/{clienteCpf}/emprestimos")
	public List<EmprestimoDTO> listarEmprestimos(@PathVariable String clienteCpf) throws ClienteNaoEncontradoException {
		List<Emprestimo> emprestimos = this.emprestimoService.listarEmprestimos(clienteCpf);
		return emprestimos.stream().map(emprestimo -> EmprestimoDTO.retornaEmprestimo(emprestimo))
				.collect(Collectors.toList());

	}

	@GetMapping("/{clienteCpf}/emprestimos/{Id}")
	public EmprestimoDTO listarEmprestimosPelaId(@PathVariable String clienteCpf, @PathVariable Long Id)
			throws ClienteNaoEncontradoException, EmprestimoIdNaoEncontradoException {

		Emprestimo emprestimo = this.emprestimoService.exibirEmprestimosPorId(clienteCpf, Id);

		return EmprestimoDTO.retornaEmprestimo(emprestimo);

	}

	@DeleteMapping("/{clienteCpf}/emprestimos/{Id}")
	public MensagemSucesso excluirEmprestimosPelaId(@PathVariable String clienteCpf, @PathVariable Long Id)
			throws ClienteNaoEncontradoException, EmprestimoIdNaoEncontradoException {

		return this.emprestimoService.excluirEmprestimosPeloId(clienteCpf, Id);

	}
}
