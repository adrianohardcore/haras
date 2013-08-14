package br.com.adrianohardcore.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adrianohardcore.model.Role;
import br.com.adrianohardcore.repository.RoleRepository;
import br.com.adrianohardcore.service.RoleService;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController  {	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RoleService roleService;
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public String list() {		
		logger.debug("Lista de regras (roles) ");		
		return "role/list";
	}
	
	@Transactional
	@RequestMapping(value="/roles", produces="application/json")
	public @ResponseBody Map<String,Object> listJqGrid(
			@RequestParam("_search") Boolean search,
    		@RequestParam(value="filters", required=false) String filters,
    		@RequestParam(value="page", required=false) Integer page,
    		@RequestParam(value="rows", required=false) Integer rows,
    		@RequestParam(value="sidx", required=false) String sidx,
    		@RequestParam(value="sord", required=false) String sord	

			) {		
		logger.info("Resposta lista de regras");		
		Order order = new Order(Direction.fromString(sord.toLowerCase()),sidx);		
		Sort sort = new Sort(order);		
		Pageable pageRequest = new PageRequest(page - 1, rows, sort);
		Page<Role> roles = roleRepository.findAll(pageRequest);			
		Map<String,Object> modelMap = new HashMap<String,Object>();
		modelMap.put("page", page);
		modelMap.put("total", roles.getTotalPages());
		modelMap.put("records", roles.getTotalElements());
		modelMap.put("rows",roles.getContent());
		return modelMap;		
	}
	
	@RequestMapping(value = "/role/form", method = RequestMethod.GET)
	public String form(ModelMap modelMap) {
		logger.info("Nova regra");
		modelMap.addAttribute("role", new Role());
		return "role/create";
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.POST)	
	public String create(@Valid Role role, BindingResult result) {
		logger.info("Salvar regra");		
		if (result.hasErrors())
			return "role/create";		
		roleService.save(role);
		return "redirect:/role";
	}
	
	
	@RequestMapping(value = "/role/{id}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
		logger.info("Editar regra");
		modelMap.addAttribute("role", roleRepository.findOne(id));		
		return "role/update";
	}
	
	@RequestMapping(value = "/role",method = RequestMethod.PUT)
	public String update(@Valid Role role, BindingResult result) {
		logger.info("Atualizar regras do formulario update");
		if (result.hasErrors())
		{			  
			logger.info("Houve erros na atualização");
			return "role/update";
		}		
		roleService.update(role);
		return "redirect:/role";
	}
}
