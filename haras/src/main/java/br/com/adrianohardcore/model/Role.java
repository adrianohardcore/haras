package br.com.adrianohardcore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.adrianohardcore.model.util.JsonDateSerializer;

@JsonAutoDetect
@Entity(name="role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	@NotNull
	@NotEmpty
	private String roleName;
	
    @DateTimeFormat    
    private Date dateCreation;
    
    @DateTimeFormat
    private Date dateModify;	
	
	private Long userCreation;
	
	private Long userModify;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
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
