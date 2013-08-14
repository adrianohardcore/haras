package br.com.adrianohardcore.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adrianohardcore.model.Cliente;
import br.com.adrianohardcore.model.Endereco;
import br.com.adrianohardcore.repository.ClienteRepository;
import br.com.adrianohardcore.repository.EnderecoRepository;
import br.com.adrianohardcore.service.ClienteService;
import br.com.adrianohardcore.service.EnderecoService;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class ClienteController  {	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	EnderecoService enderecoService;	
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public String list(ModelMap modelMap) {		
		logger.debug("Lista de clientes (clientes) ");	
		modelMap.addAttribute("clientes",clienteRepository.findAll());
		return "cliente/list";
	}
	
	@Transactional
	@RequestMapping(value="/clientes", produces="application/json")
	public @ResponseBody Map<String,Object> listJqGrid(
			@RequestParam("_search") Boolean search,
    		@RequestParam(value="filters", required=false) String filters,
    		@RequestParam(value="page", required=false) Integer page,
    		@RequestParam(value="rows", required=false) Integer rows,
    		@RequestParam(value="sidx", required=false) String sidx,
    		@RequestParam(value="sord", required=false) String sord	

			) {		
		logger.info("Resposta lista de clientes");		
		Order order = new Order(Direction.fromString(sord.toLowerCase()),sidx);		
		Sort sort = new Sort(order);		
		Pageable pageRequest = new PageRequest(page - 1, rows, sort);
		Page<Cliente> clientes = clienteRepository.findAll(pageRequest);			
		Map<String,Object> modelMap = new HashMap<String,Object>();
		modelMap.put("page", page);
		modelMap.put("total", clientes.getTotalPages());
		modelMap.put("records", clientes.getTotalElements());
		modelMap.put("rows",clientes.getContent());
		return modelMap;		
	}
	
	@RequestMapping(value = "/cliente/form", method = RequestMethod.GET)
	public String form(ModelMap modelMap) {
		logger.info("Nova cliente");
		modelMap.addAttribute("cliente", new Cliente());
		return "cliente/create";
	}	
	
	@RequestMapping(value = "/cliente/{id}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
		logger.info("Editar cliente");
		modelMap.addAttribute("cliente", clienteRepository.findOne(id));		
		modelMap.addAttribute("endereco", new Endereco());
		return "cliente/update";
	}	
	
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)	
	public void delete(@PathVariable("id") Long id) {
		logger.info("Excluindo usuário");			
		clienteService.delete(id);		
	}
	
	@RequestMapping(value = "/cliente/cliente-endereco/{id}", method = RequestMethod.DELETE)	
	public void deleteEndereco(@PathVariable("id") Long id,HttpServletResponse response) {
		logger.info("Excluindo endereco " +  id.toString() );			
		enderecoService.delete(id);
		response.setStatus(200);		
	}
	
//	@RequestMapping("finalizaTarefa")
//	public void finaliza(Long id, HttpServletResponse response) {
//	  JdbcTarefaDao dao = new JdbcTarefaDao();
//	  dao.finaliza(id);
//	  response.setStatus(200);
//	}	
	
	
	@RequestMapping(value = "/cliente/{id}/show", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {		
		logger.info("Detalhes do cliente " + id );		
		modelMap.addAttribute("cliente", clienteRepository.findOne(id));
		return "cliente/show";
	}  
	
//	@InitBinder  
//    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {  
//       binder.registerCustomEditor(Cliente.class, new ClienteEnderecoEditor(this.clienteRepository));   
//    }
	
	@RequestMapping(value = "/cliente", method = RequestMethod.POST)	
	public String create(@Valid Cliente cliente, BindingResult result) {
		logger.info("Salvar cliente");		
		if (result.hasErrors())
			return "cliente/create";		
		clienteService.save(cliente);
		return "redirect:/cliente/" + cliente.getId().toString() + "/show";
	}
	
	@RequestMapping(value = "/cliente",method = RequestMethod.PUT)
	public String update(@ModelAttribute @Valid Cliente cliente,BindingResult result) {
		logger.info("Atualizar clientes do formulario update");
		if (result.hasErrors())
		{			  
			logger.info("Houve erros na atualização");
			return "cliente/update";
		}		
		clienteService.update(cliente);
		//return "redirect:/cliente";
		return "redirect:/cliente/" + cliente.getId().toString() + "/show";
	}		
	

}
