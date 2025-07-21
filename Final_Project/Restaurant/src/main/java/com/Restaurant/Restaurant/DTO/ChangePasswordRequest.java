package com.Restaurant.Restaurant.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ChangePasswordRequest {
	
	@NotBlank(message="Current Password is required")
    private String currentPassword;
	
	@NotBlank(message="New Passord is required")
    @Pattern(
    		regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
    		message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character"
    		)
    private String newPassword;
	
	@NotBlank(message="Confirm Password is required")
    private String confirmPassword;
}
