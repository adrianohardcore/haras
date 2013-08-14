package br.com.adrianohardcore.model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.adrianohardcore.model.User;

public class PasswordValidator implements Validator{
	 
	@Override
	public boolean supports(Class<?> clazz) {
		//just validate the Customer instances
		return User.class.isAssignableFrom(clazz);
	}
 
	@Override
	public void validate(Object target, Errors errors) {
 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordForm",
			"required.passwordForm");
 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPasswordForm",
				"required.confirmPasswordForm", "Field name is required.");
 
		User user = (User)target;
 
		if(!(user.getPasswordForm().equals(user.getConfirmPasswordForm()))){
			errors.rejectValue("passwordForm", "notmatch.passwordForm");
		}
 
	}
 
}