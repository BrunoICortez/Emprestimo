package com.sistema.emprestimo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.emprestimo.entity.Cliente;
import com.sistema.emprestimo.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

	List<Emprestimo> findByCpfCliente(Cliente cpfCliente);
	Optional<Emprestimo> findByCpfClienteAndId(Cliente cpfCliente, Long Id);
	
}
