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
@Entity(name = "endereco")
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    private Cliente cliente;
	
	
	@NotNull
	@Size(min=5,max=50,message="Endereço deve conter de 5 a 50 caracteres")
	private String endereco;
	
	@NotNull
	private Integer numero;
	
	@NotNull
	private String complemento;
	
	@NotNull
	private String bairro;
	
	@NotNull
	private String cidade;
	
	@NotNull
	private String uf;
	
	@NotNull
	private String pais;
	
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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
