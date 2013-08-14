package br.com.adrianohardcore.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.adrianohardcore.model.Endereco;
import br.com.adrianohardcore.model.User;
import br.com.adrianohardcore.repository.EnderecoRepository;
import br.com.adrianohardcore.repository.UserRepository;

@Service
public class EnderecoService {
	private static final Logger logger = LoggerFactory.getLogger(EnderecoService.class);
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	UserRepository userRepository;
	

	public void save(Endereco endereco) {
		Object userName =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUserName(userName.toString());		
		endereco.setDateCreation(new Date());
		endereco.setDateModify(new Date());
		endereco.setUserCreation(user.getId());
		endereco.setUserModify(user.getId());		
		logger.info("Salvando registro");
		enderecoRepository.save(endereco);	
	}

	public void update(Endereco enderecoForm) {
		Object userName =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUserName(userName.toString());	
		
		Endereco endereco = enderecoRepository.findOne(enderecoForm.getId());
		
		endereco.setEndereco(enderecoForm.getEndereco());
		endereco.setNumero(enderecoForm.getNumero());
		endereco.setBairro(enderecoForm.getBairro());
		endereco.setComplemento(enderecoForm.getComplemento());
		endereco.setCidade(enderecoForm.getCidade());
		endereco.setUf(enderecoForm.getUf());
		endereco.setPais(enderecoForm.getPais());
		
		
		endereco.setDateCreation(new Date());
		endereco.setDateModify(new Date());
		endereco.setUserCreation(user.getId());
		endereco.setUserModify(user.getId());		
		logger.info("Atualizando registro");
		enderecoRepository.save(endereco);		
	}

	public void delete(Long id) {
		logger.info("Excluindo registro");
		enderecoRepository.delete(id);		
	}

}
