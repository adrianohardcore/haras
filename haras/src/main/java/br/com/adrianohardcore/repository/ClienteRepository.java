package br.com.adrianohardcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adrianohardcore.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
