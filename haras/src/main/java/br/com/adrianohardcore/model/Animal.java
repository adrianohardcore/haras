package br.com.adrianohardcore.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.com.adrianohardcore.model.util.JsonDateSerializer;

@JsonAutoDetect
@Entity(name = "animal")
public class Animal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull	
	@Size(min=2,max=50)
	private String nome;
	
	@ManyToOne
    private Cliente cliente;		
	
	@JsonSerialize(using=JsonDateSerializer.class)
    private Date dateCreation;
    
	@JsonSerialize(using=JsonDateSerializer.class)
    private Date dateModify;	
	
	private Long userCreation;
	
	private Long userModify;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModify() {
		return dateModify;
	}

	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}

	public Long getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(Long userCreation) {
		this.userCreation = userCreation;
	}

	public Long getUserModify() {
		return userModify;
	}

	public void setUserModify(Long userModify) {
		this.userModify = userModify;
	}


}
