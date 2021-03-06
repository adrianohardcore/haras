package br.com.adrianohardcore.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adrianohardcore.model.Cliente;
import br.com.adrianohardcore.model.Endereco;
import br.com.adrianohardcore.model.User;
import br.com.adrianohardcore.repository.ClienteRepository;
import br.com.adrianohardcore.repository.EnderecoRepository;
import br.com.adrianohardcore.repository.UserRepository;

@Service
@Transactional
public class ClienteService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	EnderecoService enderecoService;
	

	public void save(Cliente cliente) {
		logger.info("Salvando registro");
		Object userName =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUserName(userName.toString());		
		cliente.setDateCreation(new Date());
		cliente.setDateModify(new Date());
		cliente.setUserCreation(user.getId());
		cliente.setUserModify(user.getId());		
		logger.info("Salvando registro");
		clienteRepository.saveAndFlush(cliente) ;	
	}

	
	public void update(Cliente clienteForm) {
		Object userName =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUserName(userName.toString());	
		
		Cliente cliente = clienteRepository.findOne(clienteForm.getId());		
		cliente.setNome(clienteForm.getNome());
		cliente.setCpf(clienteForm.getCpf());
		cliente.setCnpj(clienteForm.getCnpj());
		cliente.setEnderecos(clienteForm.getEnderecos());	
		cliente.setAnimais(clienteForm.getAnimais());
		cliente.setDateModify(new Date());		
		cliente.setUserModify(user.getId());		
		logger.info("Atualizando registro");
		clienteRepository.save(cliente);		
	}
	
	
	public void delete(Long id) {
		logger.info("Excluindo registro");
		clienteRepository.delete(id)   ;		
	}

}
