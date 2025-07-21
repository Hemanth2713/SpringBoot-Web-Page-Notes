package com.Restaurant.Restaurant.ServiceImplementation;


import com.Restaurant.Restaurant.DTO.UserPrincipal;
import com.Restaurant.Restaurant.Model.User;
import com.Restaurant.Restaurant.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//log.info("Attempting to load user by username: {}", username);
		User user = userRepository.findByEmail(username);
		if (user == null) {
		//	log.warn("User not found with username: {}", username);
			throw new UsernameNotFoundException("user not found");
		}
	//	log.info("User found with username: {}", username);
		return new UserPrincipal(user);
	}

}
