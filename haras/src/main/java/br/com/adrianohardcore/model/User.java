package br.com.adrianohardcore.model;

import static javax.persistence.CascadeType.PERSIST;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Email;

import br.com.adrianohardcore.model.util.JsonDateSerializer;

@Entity(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 5, max = 50)
	private String name;

	@Column(unique = true)
	@NotNull
	@Size(min = 5, max = 50)
	private String userName;

	@Column(unique = true)
	//@NotNull
	@Size(min = 5, max = 50)
	@Email
	private String email;

	@NotNull	
	private String password;

	@Transient
	private String passwordForm;

	@Transient
	private String confirmPasswordForm;	

	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateCreation;

	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateModify;	

	private Long userCreation;

	private Long userModify;

	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	@ManyToMany(cascade = PERSIST)
	private List<Role> roles = new ArrayList<Role>(); 

	@PreUpdate
	public void preUpdate() {
		dateModify = new Date();
		userModify = (long) 1;        
	}

	@PrePersist
	public void prePersist() {                
		dateCreation = new Date();
		userCreation = (long) 1;
		dateModify = new Date();
		userModify = (long) 1;
	}



	@Override
	public String toString() {
		return "User [roles=" + roles + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public String getPasswordForm() {
		return passwordForm;
	}

	public void setPasswordForm(String passwordForm) {
		this.passwordForm = passwordForm;
	}

	public String getConfirmPasswordForm() {
		return confirmPasswordForm;
	}

	public void setConfirmPasswordForm(String confirmPasswordForm) {
		this.confirmPasswordForm = confirmPasswordForm;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Transient
	public List<String> getRoleNames() {
		List<String> roleNames = new ArrayList<String>();
		for (Role role : getRoles()) {
			roleNames.add(role.getRoleName());
		}

		return roleNames;
	}
}
