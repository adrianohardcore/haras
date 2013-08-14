package br.com.adrianohardcore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adrianohardcore.model.User;

public interface UserRepository extends JpaRepository<User, Long> {	
	User findByUserName(String userName);	
	User findByEmail(String email);	
	Page<User> findByNameLike(String name, Pageable pageRequest);
	
}
