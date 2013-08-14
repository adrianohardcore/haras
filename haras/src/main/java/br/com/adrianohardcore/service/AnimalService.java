package br.com.adrianohardcore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adrianohardcore.model.Animal;
import br.com.adrianohardcore.repository.AnimalRepository;

@Service
public class AnimalService {
	
	private static final Logger logger = LoggerFactory.getLogger(AnimalService.class);
	
	@Autowired
	AnimalRepository animalRepository;
	
	public void save(Animal animal){
		logger.info("Salvando registro");
		animalRepository.saveAndFlush(animal);		
	}
	
	public void update(Animal animal){
		logger.info("Atualizando registro");
		animalRepository.saveAndFlush(animal);		
	}

}
