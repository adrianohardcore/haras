package br.com.adrianohardcore.model;

import static javax.persistence.CascadeType.PERSIST;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

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
	@Size(min = 5, max = 50)
	@Email
	private String email;

	@NotNull	
	private String password;

	@Transient
	private String passwordForm;

	@Transient
	private String confirmPasswordForm;	


	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	@ManyToMany(cascade = PERSIST)
	private List<Role> roles = new ArrayList<Role>(); 





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
