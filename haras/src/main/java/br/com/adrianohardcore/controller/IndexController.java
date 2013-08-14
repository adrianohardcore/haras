package br.com.adrianohardcore.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.adrianohardcore.service.RoleService;
import br.com.adrianohardcore.service.UserService;

@Controller

public class IndexController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;	
	
	protected static Logger logger = Logger.getLogger("service");
	
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping("/")
	public String getHomePage() {	
		logger.debug("PÃ¡gina inicial");
		System.out.println("PÃ¡gina inicial");		
		return "index/home";
	}	
	
	@RequestMapping("/404")
	public String notFound() {	
		logger.debug("Página não encontrada");				
		return "index/404";
	}
}