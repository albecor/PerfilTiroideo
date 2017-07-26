package com.spring.medical.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.medical.model.Role;
import com.spring.medical.model.User;
import com.spring.medical.service.UserService;

/**
 * 
 * Set detail service for user logged
 *
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserService userService;

	/**
	 * set user details
	 */
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ndivalue) throws UsernameNotFoundException {
		User user = userService.selectByNdivalue(ndivalue);
		// logger.info("Username : {}", ndivalue);
		// logger.info("User : {}", user);
		if (user == null) {
			// logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getNdivalue(), user.getPassword(), true,
				true, true, true, getGrantedAuthorities(user));
	}

	/**
	 * select roles for the user logged
	 * 
	 * @param user
	 * @return roles list
	 */
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : user.getRoles()) {
			// logger.info("UserProfile : {}", role);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getType()));
		}
		// logger.info("authorities : {}", authorities);
		return authorities;
	}

}
