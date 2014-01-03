package br.com.adrianohardcore.model;

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

import br.com.adrianohardcore.model.util.ModeloGeral;

@JsonAutoDetect
@Entity(name = "cliente")
public class Cliente extends ModeloGeral{	
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
	
	@JsonIgnore
	@Valid
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY )
    @JoinColumn(name="CLIENTE_ID")
    private List<Endereco> enderecos;
	
	@JsonIgnore
	@Valid
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY )
    @JoinColumn(name="CLIENTE_ID")
    private List<Animal> animais;

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

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}
}
