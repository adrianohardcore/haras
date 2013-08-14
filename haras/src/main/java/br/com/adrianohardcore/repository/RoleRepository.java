package br.com.adrianohardcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adrianohardcore.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByRoleName(String string);

}
