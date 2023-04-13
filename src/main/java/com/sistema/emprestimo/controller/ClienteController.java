package com.sistema.emprestimo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.emprestimo.dto.ClienteDTO;
import com.sistema.emprestimo.entity.Cliente;
import com.sistema.emprestimo.exception.ClienteNaoEncontradoException;
import com.sistema.emprestimo.service.ClienteService;
import com.sistema.emprestimo.service.MensagemSucesso;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		
		this.clienteService = clienteService;
	
	}	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente cadastraCliente(@Valid @RequestBody Cliente cliente) {
		return this.clienteService.cadastrarCliente(cliente);
	}

	@GetMapping
	public List<ClienteDTO> listarTodosOsClientes() {
		List<Cliente> clientes = this.clienteService.listarTodosOsClientes();
		return clientes.stream().map(cliente -> ClienteDTO.retornaCliente(cliente)).collect(Collectors.toList());
	}

	@GetMapping("/{cpf}")
	public ClienteDTO exibirClientePeloCpf(@PathVariable String cpf) throws ClienteNaoEncontradoException {
		Cliente cliente = this.clienteService.exibirClientesPeloCpf(cpf);
		return ClienteDTO.retornaCliente(cliente);
	}

	@PutMapping("/{cpf}")
	public ClienteDTO atualizarClientes(@PathVariable String cpf, @Valid @RequestBody ClienteDTO clientedto)
			throws ClienteNaoEncontradoException {
		Cliente clienteRequest = ClienteDTO.retornaCliente(clientedto);
		Cliente clienteAlterado = this.clienteService.AtualizarClientes(cpf, clienteRequest);
		return ClienteDTO.retornaCliente(clienteAlterado);
	}

	@DeleteMapping("/{cpf}")
	public MensagemSucesso excluirCliente(@PathVariable String cpf) throws ClienteNaoEncontradoException {
		return this.clienteService.excluirClientesPeloCpf(cpf);
	}
}