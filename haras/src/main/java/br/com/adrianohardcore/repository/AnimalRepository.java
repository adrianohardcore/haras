package br.com.adrianohardcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adrianohardcore.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>{

}
