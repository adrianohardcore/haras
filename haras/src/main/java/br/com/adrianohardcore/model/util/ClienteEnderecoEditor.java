package br.com.adrianohardcore.model.util;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.adrianohardcore.model.Cliente;
import br.com.adrianohardcore.repository.ClienteRepository;

public class ClienteEnderecoEditor extends PropertyEditorSupport{
	
	private ClienteRepository clienteRepository;  

	private static final Logger logger = LoggerFactory.getLogger(ClienteEnderecoEditor.class);

	public ClienteEnderecoEditor(ClienteRepository clienteRepository) {  
		this.clienteRepository = clienteRepository;  
	}  

	@Override
    public void setAsText(String text) throws IllegalArgumentException {		
		logger.info("BINDER: Valor enviado pelo formulário: " + text);
		String regex = "[\\d]+"; 
		if(text.matches(regex)){
			logger.info("BINDER:Teste: " + text);
		}
		    Long id = new Long(text);  
		    Cliente cliente = clienteRepository.findOne(id);	      
		    super.setValue(cliente);  
		
    }


}
