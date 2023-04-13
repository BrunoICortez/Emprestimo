package com.sistema.emprestimo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.emprestimo.entity.Cliente;
import com.sistema.emprestimo.exception.ClienteNaoEncontradoException;
import com.sistema.emprestimo.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {

		this.clienteRepository = clienteRepository;

	}

	public List<Cliente> listarTodosOsClientes() {
		return this.clienteRepository.findAll();
	}

	public Cliente exibirClientesPeloCpf(String cpf) throws ClienteNaoEncontradoException {
		if (this.clienteRepository.existsById(cpf)) {
			return this.clienteRepository.findById(cpf).get();
		}
		throw new ClienteNaoEncontradoException(cpf);
	}

	public MensagemSucesso excluirClientesPeloCpf(String cpf) throws ClienteNaoEncontradoException {
		if (this.clienteRepository.existsById(cpf)) {
			this.clienteRepository.deleteById(cpf);
			MensagemSucesso msg = new MensagemSucesso();
			msg.setMensagem("Deletado com sucesso");
			return msg;
		}
		throw new ClienteNaoEncontradoException(cpf);
	}

	public Cliente cadastrarCliente(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	public Cliente AtualizarClientes(String cpf, @Valid Cliente cliente) throws ClienteNaoEncontradoException {
		if (this.clienteRepository.existsById(cpf)) {
			Cliente clienteCad = this.clienteRepository.findById(cpf).get();
			cliente.setCpf(cpf);

			if (cliente.getCpf() == null) {
				cliente.setCpf(clienteCad.getCpf());
			}

			if (cliente.getNome() == null) {
				cliente.setNome(clienteCad.getNome());
			}

			if (cliente.getTelefone() == null) {
				cliente.setTelefone(clienteCad.getTelefone());
			}

			if (cliente.getEndereco() == null) {
				cliente.setEndereco(clienteCad.getEndereco());
			}
			if (cliente.getRendimentoMensal() == null) {
				cliente.setRendimentoMensal(clienteCad.getRendimentoMensal());
			}

			if (cliente.getEndereco().getRua() == null) {
				cliente.getEndereco().setRua(clienteCad.getEndereco().getRua());
			}

			if (cliente.getEndereco().getNumero() == null) {
				cliente.getEndereco().setNumero(clienteCad.getEndereco().getNumero());
			}

			if (cliente.getEndereco().getCep() == null) {
				cliente.getEndereco().setCep(clienteCad.getEndereco().getCep());
			}

			return this.clienteRepository.save(cliente);
		}

		throw new ClienteNaoEncontradoException(cpf);
	}
}