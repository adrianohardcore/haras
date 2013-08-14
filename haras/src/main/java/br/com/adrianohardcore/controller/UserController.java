package br.com.adrianohardcore.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adrianohardcore.model.Role;
import br.com.adrianohardcore.model.User;
import br.com.adrianohardcore.model.util.UserRolesPropertyEditor;
import br.com.adrianohardcore.model.validator.PasswordValidator;
import br.com.adrianohardcore.repository.RoleRepository;
import br.com.adrianohardcore.repository.UserRepository;
import br.com.adrianohardcore.service.UserService;

@Controller
public class UserController {	

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);


	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String list() {
		logger.info("Lista de usuários");		
		return "user/list";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listGrid()
	{
		logger.info("Lista de usuários com datatables");		
		logger.debug("Lista de usuários com datatables");
		return "user/listgrid";
	}	

	@Transactional
	@RequestMapping(value="/userlist", produces="application/json")
	public @ResponseBody Map<String,Object> listJqGrid(
			@RequestParam("_search") Boolean search,
    		@RequestParam(value="filters", required=false) String filters,
    		@RequestParam(value="page", required=false) Integer page,
    		@RequestParam(value="rows", required=false) Integer rows,
    		@RequestParam(value="sidx", required=false) String sidx,
    		@RequestParam(value="sord", required=false) String sord	

			) {		
		logger.info("Resposta lista de usuários");		
		Order order = new Order(Direction.fromString(sord.toLowerCase()),sidx);		
		Sort sort = new Sort(order);		
		Pageable pageRequest = new PageRequest(page - 1, rows, sort);
		Page<User> users = userRepository.findAll(pageRequest);			
		Map<String,Object> modelMap = new HashMap<String,Object>();
		modelMap.put("page", page);
		modelMap.put("total", users.getTotalPages());
		modelMap.put("records", users.getTotalElements());
		modelMap.put("rows",users.getContent());
		return modelMap;		
	}
	
	@Transactional
	@RequestMapping(value="/userlistDatatables", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> datatables(
			@RequestParam(value="sEcho",required=false) String sEcho,
			@RequestParam(value="iDisplayStart",required=false) Integer iDisplayStart,
			@RequestParam(value="iDisplayLength",required=false) Integer  iDisplayLength,			
			@RequestParam(value="mDataProp_0",required=false) String mDataProp_0,
			@RequestParam(value="mDataProp_1",required=false) String mDataProp_1,
			@RequestParam(value="mDataProp_2",required=false) String mDataProp_2,
			@RequestParam(value="mDataProp_3",required=false) String mDataProp_3,			
			@RequestParam(value="iSortCol_0",required=false) Integer iSortCol_0,
			@RequestParam(value="sSortDir_0",required=false) String sSortDir_0,
			@RequestParam(value="sSearch",required=false) String search			

			) {		
		logger.info("Resposta lista de usuários");		

		String[] colunas = {mDataProp_0,mDataProp_1,mDataProp_2,mDataProp_3};
		Order order = new Order(Direction.fromString(sSortDir_0.toLowerCase()),colunas[iSortCol_0]);		
		Sort sort = new Sort(order);
		Pageable pageRequest = new PageRequest(iDisplayStart / iDisplayLength, iDisplayLength, sort);	

		Page<User> users;
		if (search == ""){			
			users = userRepository.findAll(pageRequest);
		}
		else{
			users = userRepository.findByNameLike("%" + search + "%",pageRequest);
		}
		//List<UserDto> userDtos = UserMapper.map(users.getContent());		
		Map<String,Object> modelMap = new HashMap<String,Object>();
		modelMap.put("sEcho", sEcho);
		modelMap.put("iTotalRecords", users.getTotalElements());
		modelMap.put("iTotalDisplayRecords", users.getTotalElements());
		//modelMap.put("aaData",userDtos);
		modelMap.put("aaData",users.getContent());
		return modelMap;		
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)	
	public void delete(@PathVariable("id") Long id) {
		logger.info("Excluindo usuário");			
		userService.delete(id);		
	}

	@RequestMapping(value = "/user/form", method = RequestMethod.GET)
	public String form(ModelMap modelMap) {
		logger.info("Novo usuário");
		modelMap.addAttribute("user", new User());
		return "user/create";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)	
	public String create(@Valid User user, BindingResult result) {
		logger.info("Salvar usuário");		
		PasswordValidator passwordValidator = new PasswordValidator();
		passwordValidator.validate(user,result);		
		if (result.hasErrors())
			return "user/create";		
		userService.save(user);
		return "redirect:/user";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user/{id}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
		logger.info("Editar usuário");
		modelMap.addAttribute("user", userRepository.findOne(id));
		modelMap.addAttribute("roleList", roleRepository.findAll());
		return "user/update";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user",method = RequestMethod.PUT)
	public String update(@ModelAttribute @Valid User user, BindingResult result,Model model) {
		logger.info("Atualizar usuários do formulario update");
		
		if (user.getPasswordForm().isEmpty() == false){
			logger.info("Senha: " + user.getPasswordForm());
			PasswordValidator passwordValidator = new PasswordValidator();
			passwordValidator.validate(user,result);			
		}
		
		if (result.hasErrors())
		{			
			model.addAttribute("roleList", roleRepository.findAll());			
			return "user/update";
		}
		
		userService.update(user);
		return "redirect:/user";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user/{id}/show", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {		
		logger.info("Detalhes do usuário " + id );	
		modelMap.addAttribute("user", userRepository.findOne(id));
		return "user/show";
	}  
	
	@InitBinder  
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {  
       binder.registerCustomEditor(Role.class, new UserRolesPropertyEditor(this.roleRepository));  
    }  

}