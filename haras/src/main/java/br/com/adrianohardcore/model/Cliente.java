package br.com.adrianohardcore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.com.adrianohardcore.model.util.JsonDateSerializer;

@JsonAutoDetect
@Entity(name = "cliente")
public class Cliente implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull	
	@Size(min=5,max=50)
	private String nome;
	
	@NotNull		
	private String cpf;
	
	@NotNull		
	private String cnpj;	
	
//	@JsonIgnore
//	@Valid
//	@Embedded
//	@OneToMany(mappedBy="cliente", cascade= CascadeType.ALL , fetch = FetchType.LAZY)	
//    private List<Endereco> enderecos;
	
	@JsonIgnore
	@Valid
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY )
    @JoinColumn(name="CLIENTE_ID")
    private List<Endereco> enderecos;
	
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "proposta", orphanRemoval=true)    
//    @LazyCollection(LazyCollectionOption.FALSE)    
//     private List<Posicionamento> posicionamentos;
	
	
	
	
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
