package com.Restaurant.Restaurant.utils;


import com.Restaurant.Restaurant.Model.User;
import com.Restaurant.Restaurant.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomUtils {

	private static UserRepository userRepository;
	
	@Autowired
    public void setUserRepository(UserRepository userRepository) {
        CustomUtils.userRepository = userRepository;
    }
	
	public static User getCurrentLoggedInUser() {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			username = userDetails.getUsername();
		}else {
			username = principal.toString();
		}
		return CustomUtils.userRepository.findByEmail(username);
	}
}
