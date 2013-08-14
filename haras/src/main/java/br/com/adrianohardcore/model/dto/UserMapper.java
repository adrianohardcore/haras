package br.com.adrianohardcore.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.adrianohardcore.model.User;
public class UserMapper {
	private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);

	public static UserDto map(User user) {
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setName(user.getName());
			dto.setUserName(user.getUserName());
			dto.setEmail(user.getEmail());			
			return dto;
	}
	
	public static List<UserDto> map(List<User> users) {
		logger.info("Mapear atributos de User na classe UserDto");	
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (User user: users) {			
			dtos.add(map(user));
		}
		return dtos;
	}
}