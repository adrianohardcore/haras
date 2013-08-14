package br.com.adrianohardcore.controller;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.adrianohardcore.service.InitializationService;

@Controller
@RequestMapping
public class AccessController {		
	protected static Logger logger = Logger.getLogger("service");	
	
	@Autowired
	InitializationService initializationService;

	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required=false) String message) {		
		logger.debug("Login");		
		model.addAttribute("message", message);
		return "index/login";
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/denied")
 	public String denied() {		
		return "index/denied";
	}
	
	@RequestMapping(value = "/login/failure")
 	public String loginFailure() {
		logger.debug("Acesso negado");
		String message = "Login Failure!";
		return "redirect:/login?message="+message;
	}
	
	@RequestMapping(value = "/logout/success")
 	public String logoutSuccess() {
		String message = "Logout Success!";
		return "redirect:/login?message="+message;
	}
	
	@PostConstruct
	public void inicia(){		
		initializationService.inicia();			
		System.out.println("Rodando!");
	}
}