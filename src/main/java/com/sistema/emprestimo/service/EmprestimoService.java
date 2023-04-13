package com.sistema.emprestimo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.emprestimo.entity.Cliente;
import com.sistema.emprestimo.entity.Emprestimo;
import com.sistema.emprestimo.exception.ClienteNaoEncontradoException;
import com.sistema.emprestimo.exception.EmprestimoIdNaoEncontradoException;
import com.sistema.emprestimo.exception.ValorEmprestimoUltrapassadoException;
import com.sistema.emprestimo.repository.ClienteRepository;
import com.sistema.emprestimo.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	private EmprestimoRepository emprestimoRepository;
	private ClienteRepository clienteRepository;
	
	@Autowired
	public EmprestimoService(EmprestimoRepository emprestimoRepository, ClienteRepository clienteRepository) {
		
		this.emprestimoRepository = emprestimoRepository;
		this.clienteRepository = clienteRepository;
		
	}
	

	public List<Emprestimo> listarEmprestimos(String clienteCpf) throws ClienteNaoEncontradoException {

		Optional<Cliente> clienteOptional = this.clienteRepository.findById(clienteCpf);

		if (clienteOptional.isPresent()) {
			Cliente cliente = clienteOptional.get();
			return this.emprestimoRepository.findByCpfCliente(cliente);
		}

		throw new ClienteNaoEncontradoException(clienteCpf);
	}

	public Emprestimo exibirEmprestimosPorId(String clienteCpf, Long Id)	throws ClienteNaoEncontradoException, EmprestimoIdNaoEncontradoException {

		Optional<Cliente> clienteO = this.clienteRepository.findById(clienteCpf);

		if (clienteO.isPresent()) {

			Cliente cliente = clienteO.get();

			Optional<Emprestimo> emprestimoO = this.emprestimoRepository.findByCpfClienteAndId(cliente, Id);

			if (this.emprestimoRepository.existsById(Id)) {

				return emprestimoO.get();

			} else {
				throw new EmprestimoIdNaoEncontradoException(Id);
			}
		}

		throw new ClienteNaoEncontradoException(clienteCpf);
	}

	public Emprestimo cadastrarEmprestimo(Emprestimo emprestimo, String clienteCpf)		throws ClienteNaoEncontradoException, ValorEmprestimoUltrapassadoException {

		Optional<Cliente> clienteO = clienteRepository.findById(clienteCpf);
		if (this.clienteRepository.existsById(clienteCpf)) {

			Cliente cliente = clienteO.get();

			BigDecimal m = new BigDecimal(10);
			BigDecimal rendimentoMensalTotal = cliente.getRendimentoMensal().multiply(m);

			BigDecimal emprestimoQts = new BigDecimal(emprestimoRepository.findByCpfCliente(cliente).size());
			BigDecimal total = emprestimo.getvalorEmprestimoInicial()
					.add(emprestimoQts.multiply(emprestimo.getvalorEmprestimoInicial()));

			if (total.compareTo(rendimentoMensalTotal) > 0) {
				throw new ValorEmprestimoUltrapassadoException(total, rendimentoMensalTotal);
			}
			emprestimo.setvalorEmprestimoFinal(emprestimo.getRelacionamento().calculaValorFinal(emprestimo.getvalorEmprestimoInicial(), emprestimoQts));
			emprestimo.setCliente(cliente);
			return emprestimoRepository.save(emprestimo);
		}
		throw new ClienteNaoEncontradoException(clienteCpf);
	}

	public MensagemSucesso excluirEmprestimosPeloId(String clienteCpf, Long Id)		throws ClienteNaoEncontradoException, EmprestimoIdNaoEncontradoException {
		if (this.clienteRepository.existsById(clienteCpf)) {
			if (this.emprestimoRepository.existsById(Id)) {
				this.emprestimoRepository.deleteById(Id);
				MensagemSucesso mensagem = new MensagemSucesso();
				mensagem.setMensagem("Deletado com sucesso");
				return mensagem;
			} else {
				throw new EmprestimoIdNaoEncontradoException(Id);
			}
		}
		throw new ClienteNaoEncontradoException(clienteCpf);
	}
}
