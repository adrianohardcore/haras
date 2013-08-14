package br.com.adrianohardcore.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);


	@ExceptionHandler(IOException.class)
	public String handleIOException(IOException ex) {
		logger.info("Exceção encontrada: " + ex );
		return "index/exception";
	}

	@ExceptionHandler(java.lang.IllegalArgumentException.class)
	public String handleIOException2(java.lang.IllegalArgumentException ex) {
		logger.info("Exceção encontrada: " + ex );
		return "index/exception";
	}	
	
	@ExceptionHandler
	public ModelAndView handleException(org.springframework.dao.InvalidDataAccessApiUsageException ex ) {
		logger.info("Exceção encontrada: " + ex );		
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index/exception");
	    modelAndView.addObject("exception", ex);		
	    return modelAndView;		
	}
	
	@ExceptionHandler(NoSuchRequestHandlingMethodException.class)
	public ModelAndView handleException(NoSuchRequestHandlingMethodException ex ) {
		                                
		logger.info("Exceção encontrada: " + ex );		
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index/exception");
	    modelAndView.addObject("exception", ex);		
	    return modelAndView;		
	}	//java.lang.NullPointerException
	
	@ExceptionHandler
	public ModelAndView handleException(java.lang.NullPointerException ex ) {
		                                
		logger.info("Exceção encontrada: " + ex );		
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index/exception");
	    modelAndView.addObject("exception", ex);		
	    return modelAndView;		
	}
	
	@ExceptionHandler
	public ModelAndView handleException(java.lang.IllegalStateException ex ) {  
		                                
		logger.info("Exceção encontrada: " + ex );		
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index/exception");
	    modelAndView.addObject("exception", ex);		
	    return modelAndView;		
	}
	
	@ExceptionHandler
	public ModelAndView handleException(org.apache.tiles.util.TilesIOException ex ) {  
		                                
		logger.info("Exceção encontrada: " + ex );		
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index/exception");
	    modelAndView.addObject("exception", ex);		
	    return modelAndView;		
	}
	
	@ExceptionHandler
	public ModelAndView handleException(org.springframework.web.util.NestedServletException ex ) {  
		                                
		logger.info("Exceção encontrada: " + ex );		
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index/exception");
	    modelAndView.addObject("exception", ex);		
	    return modelAndView;		
	}	
	
	
	@ExceptionHandler
	public ModelAndView handleException(org.apache.tiles.impl.CannotRenderException ex ) {  
		                                
		logger.info("Exceção encontrada: " + ex );		
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index/exception");
	    modelAndView.addObject("exception", ex);		
	    return modelAndView;		
	}	
	
	
	
	
	@ExceptionHandler
	public String handleException(org.springframework.security.access.AccessDeniedException ex ) {
		logger.info("Usuário não logado!");
	    return "index/login";		
	}	
	
	@ExceptionHandler
	public ModelAndView handleException(java.lang.Exception ex ) {
		logger.info("Exceção encontrada: " + ex );		
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index/exception");
	    modelAndView.addObject("exception", ex);		
	    return modelAndView;		
	}	
	
}
