package br.com.adrianohardcore.service;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import br.com.adrianohardcore.model.User;
import br.com.adrianohardcore.repository.UserRepository;

public class CustomAuthenticationManager implements AuthenticationManager {

	protected static Logger logger = Logger.getLogger("service");

	@Autowired
	UserRepository userRepository;

	private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

	@Transactional
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		logger.debug("Performing custom authentication");
		br.com.adrianohardcore.model.User user = null;

		try {
			user = userRepository.findByUserName(auth.getName());
			logger.debug("Usuário: " + user.getUserName()   );

		} catch (Exception e) {
			logger.error("Usuário não existe!");
			throw new BadCredentialsException("User does not exists!");
		}

		if (passwordEncoder.isPasswordValid(user.getPassword(),(String) auth.getCredentials(), null) == false) {
			logger.error("Senha errada");
			throw new BadCredentialsException("Wrong password!");
		}

		logger.debug("Usuário e senha corretos");
		
		Collection<GrantedAuthority> grantedAuthorities = toGrantedAuthorities(user);		
		return new UsernamePasswordAuthenticationToken(auth.getName(),auth.getCredentials(), grantedAuthorities);
	}

    public static Collection<GrantedAuthority> toGrantedAuthorities(User user) {
        List<GrantedAuthority> result = newArrayList();
        List<String> roles = user.getRoleNames();  

        for (String role : roles) {
            result.add(new SimpleGrantedAuthority(role));
        }
        return result;
    }
}
