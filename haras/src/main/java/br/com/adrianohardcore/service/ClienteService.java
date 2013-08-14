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
		Object userName =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUserName(userName.toString());		
		cliente.setDateCreation(new Date());
		cliente.setDateModify(new Date());
		cliente.setUserCreation(user.getId());
		cliente.setUserModify(user.getId());		
		logger.info("Salvando registro");
		clienteRepository.save(cliente);	
	}

	
	public void update(Cliente clienteForm) {
		Object userName =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUserName(userName.toString());	
		
		Cliente cliente = clienteRepository.findOne(clienteForm.getId());		
		cliente.setNome(clienteForm.getNome());
		cliente.setCpf(clienteForm.getCpf());
		cliente.setCnpj(clienteForm.getCnpj());
			
//		Boolean temEnderecos = false;
//		if (clienteForm.getEnderecos()!= null){
//			for (Endereco endereco : clienteForm.getEnderecos()) {
//				//if (!endereco.getEndereco().isEmpty()){
//					//endereco.setCliente(cliente);		
//					//endereco.setDateCreation(new Date());
//					//endereco.setDateModify(new Date());
//					//endereco.setUserCreation(user.getId());
//					//endereco.setUserModify(user.getId());			
//					
//					logger.info("ID " + endereco.getId());
//					//logger.info("Cliente " + endereco.getCliente().getNome());
//					logger.info("Endereco " + endereco.getEndereco());
//					logger.info("Número " + endereco.getNumero());
//					logger.info("Bairro " + endereco.getBairro());
//					temEnderecos = true;
//				//}			
//			}	
//		}
		
//		if (temEnderecos)
//			cliente.setEnderecos(clienteForm.getEnderecos());
		
		cliente.setEnderecos(clienteForm.getEnderecos());		
		cliente.setDateModify(new Date());		
		cliente.setUserModify(user.getId());		
		logger.info("Atualizando registro");
		clienteRepository.save(cliente);		
	}

	public void delete(Long id) {
		logger.info("Excluindo registro");
		clienteRepository.delete(id);		
	}

}
