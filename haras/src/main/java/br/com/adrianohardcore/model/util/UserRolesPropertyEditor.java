package br.com.adrianohardcore.model.util;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.adrianohardcore.model.Role;
import br.com.adrianohardcore.repository.RoleRepository;

public class UserRolesPropertyEditor extends PropertyEditorSupport {

	private RoleRepository roleRepository;  

	private static final Logger logger = LoggerFactory.getLogger(UserRolesPropertyEditor.class);

	public UserRolesPropertyEditor(RoleRepository rolRepository) {  
		this.roleRepository = rolRepository;  
	}  

	@Override  
	public void setAsText(String text) throws IllegalArgumentException {		  
		logger.info("Valor enviado pelo formulário: " + text);	
		Long roleId = new Long(text);  
		Role role = roleRepository.findOne(roleId);  
		super.setValue(role);  
	}  
}
