package br.com.adrianohardcore.controller;

import java.util.List;
import java.util.Set;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adrianohardcore.model.Animal;
import br.com.adrianohardcore.repository.AnimalRepository;
import br.com.adrianohardcore.service.AnimalService;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class AnimalController {
	@Autowired
	AnimalRepository animalRepository;

	@Autowired
	AnimalService animalService;

	private static final Logger logger = LoggerFactory.getLogger(AnimalController.class);

	@Transactional
	@RequestMapping(value = "/animais", produces = "application/json")
	public @ResponseBody
	HttpEntity<List<Animal>> listDojo(
			@RequestHeader(value = "Range", required = false) String range,
			@RequestParam(value = "sort", required = false) String sort,
			HttpServletRequest request) {

		HttpHeaders headers = new HttpHeaders();
		List<Animal> body = null;

		logger.info("Dojo range " + range);
		logger.info("Dojo sort " + sort);

		Integer regIni = Integer.parseInt((range.split("=")[1]).split("-")[0]);
		Integer regFinal = Integer.parseInt((range.split("=")[1]).split("-")[1]);
		Integer rows = (regFinal - regIni);		
		logger.info("Quantidade de linhas: " + rows.toString());

		Order order = new Order(Direction.fromString("asc"), "nome");
		Sort sortList = new Sort(order);

		if (sort != null) {
			String coluna = (String) sort.subSequence(1, sort.length());
			String ordem = (String) sort.subSequence(0, 1);
			if (ordem.equalsIgnoreCase("-")) {
				ordem = "desc";
			} else {
				ordem = "asc";
			}
			logger.info("Ordenar a coluna " + coluna + " com a ordenação "	+ ordem);

			order = new Order(Direction.fromString(ordem), coluna);
			sortList = new Sort(order);
		}

		Integer pagina = 0;
		pagina = (int) Math.floor((regFinal / rows));

		logger.info("Número de página " + pagina.toString());

		Pageable pageRequest = new PageRequest(pagina - 1, rows + 1, sortList);
		Page<Animal> animais = animalRepository.findAll(pageRequest);
		long count = animais.getTotalElements();
		body = animais.getContent();
									
		headers.add("Content-Range",getContentRangeValue(regIni,body.size(),count));
		return new HttpEntity<List<Animal>>(body, headers);

	}

	@RequestMapping(value = "/animal", method = RequestMethod.GET)
	public String list() {
		logger.debug("Lista de animais");
		return "animal/list";
	}

	@RequestMapping(value = "/animal/form", method = RequestMethod.GET)
	public String form(ModelMap modelMap) {
		logger.info("Nova regra");
		modelMap.addAttribute("animal", new Animal());
		return "animal/create";
	}

	@RequestMapping(value = "/animal", method = RequestMethod.POST)
	public String create(@Valid Animal animal, BindingResult result) {
		logger.info("Salvar regra");
		if (result.hasErrors())
			return "animal/create";
		animalService.save(animal);
		return "redirect:/animal";
	}

	@RequestMapping(value = "/animal/{id}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
		logger.info("Editar regra");
		modelMap.addAttribute("animal", animalRepository.findOne(id));
		return "animal/update";
	}

	@RequestMapping(value = "/animal", method = RequestMethod.PUT)
	public String update(@Valid Animal animal, BindingResult result) {
		logger.info("Atualizar regras do formulario update");
		if (result.hasErrors()) {
			logger.info("Houve erros na atualização");
			return "animal/update";
		}
		animalService.update(animal);
		return "redirect:/animal";
	}

	private String getContentRangeValue(Integer firstResult,Integer resultCount, Long totalCount) {
		StringBuilder value = new StringBuilder("items " + firstResult + "-");
		if (resultCount == 0) {
			value.append("0");
		} else {
			value.append(firstResult + resultCount - 1);
		}
		value.append("/" + totalCount);
		return value.toString();
	}

}
