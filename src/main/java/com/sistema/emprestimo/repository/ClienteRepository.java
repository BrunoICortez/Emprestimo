package com.sistema.emprestimo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.emprestimo.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

}
