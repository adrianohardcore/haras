package br.com.adrianohardcore.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.adrianohardcore.model.Role;
import br.com.adrianohardcore.model.User;
import br.com.adrianohardcore.repository.RoleRepository;
import br.com.adrianohardcore.repository.UserRepository;

@Service
public class RoleService {	
	
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	RoleRepository  roleRepository;
	
	@Autowired
	UserRepository userRepository;

	public boolean isEmpty() {
		if (roleRepository.count() == 0){
			return true;
		}
		return false;
	}
	
	public void save(Role role) {		
		Object userName =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUserName(userName.toString());		
		role.setDateCreation(new Date());
		role.setDateModify(new Date());
		role.setUserCreation(user.getId());
		role.setUserModify(user.getId());		
		logger.info("Salvando registro");
		roleRepository.save(role);		
	}

	public void update(Role roleForm) {
		Object userName =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUserName(userName.toString());		
		
		Role role = roleRepository.findOne(roleForm.getId());	
		role.setRoleName(roleForm.getRoleName());		
		role.setDateCreation(new Date());
		role.setDateModify(new Date());
		role.setUserCreation(user.getId());
		role.setUserModify(user.getId());		
		logger.info("Salvando registro");
		roleRepository.save(role);		
	}
}
