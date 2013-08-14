package br.com.adrianohardcore.service;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adrianohardcore.model.Role;
import br.com.adrianohardcore.model.User;
import br.com.adrianohardcore.repository.RoleRepository;
import br.com.adrianohardcore.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Transactional
	public void save(User user){
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		user.setPassword(passwordEncoder.encodePassword(user.getPasswordForm(),null));				
		Role role = roleRepository.findByRoleName("USER");
		if (role == null){
			role = new Role();
			role.setRoleName("USER");			
		}		
		List<Role> roles = newArrayList();
		roles.add(role);		
		user.setRoles(roles);		
		userRepository.saveAndFlush(user);		
	}
	
	public void update(User userForm){		
		User user = userRepository.findOne(userForm.getId());
		if (user != null)
		{
			user.setName(userForm.getName());
			user.setUserName(userForm.getUserName());
			user.setEmail(userForm.getEmail());
			user.setRoles(userForm.getRoles());
			if (userForm.getPasswordForm().isEmpty() == false ){
				Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
				user.setPassword(passwordEncoder.encodePassword(userForm.getPasswordForm(),null));
				logger.info("Atualizando senha");
			}			
			userRepository.saveAndFlush(user);
			logger.info("Usuário atualizado");
		}		
	}

	public void delete(Long id) {
		userRepository.delete(id);		
	}

	public Boolean isEmpty() {
		if (userRepository.count() == 0){
			return true;
		}		
		return false;
	}
}
