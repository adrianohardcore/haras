package br.com.adrianohardcore.service;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adrianohardcore.model.Role;
import br.com.adrianohardcore.model.User;
import br.com.adrianohardcore.repository.RoleRepository;
import br.com.adrianohardcore.repository.UserRepository;

@Service
@Transactional
public class InitializationService {

	@Autowired
	RoleService roleService;

	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void inicia(){
		if (userService.isEmpty() && roleService.isEmpty()){
			createUserAdmin();
		}
	}
		
	private void createUserAdmin(){
		User user = new User();
		user.setName("Administrador");
		user.setEmail("admin@system.com");					
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		user.setPassword(passwordEncoder.encodePassword("admin",null));
		user.setUserName("admin");
		List<Role> roles = newArrayList();		
		Role roleAdmin = new Role();
		roleAdmin.setRoleName("ADMIN");		
		Role roleUser = new Role();
		roleUser.setRoleName("USER");		
		roles.add(roleAdmin);
		roles.add(roleUser);		
		user.setRoles(roles);		
		userRepository.save(user);		
	}	
}