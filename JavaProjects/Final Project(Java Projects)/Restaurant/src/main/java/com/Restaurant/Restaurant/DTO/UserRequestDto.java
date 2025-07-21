package com.Restaurant.Restaurant.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequestDto {

	@NotBlank(message="Name is required")
	private String name;
	
	@NotBlank(message="Email is required")
	@Email(message="Invalid Email Format")
	private String email;
	
	@NotBlank(message="Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
         regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
         message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character"
         )
	private String password;
	
	@NotBlank(message="Confirm Password is required")
	private String confirmPassword;
	
	@NotBlank(message="Mobile number is required")
   @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Mobile number must be a valid 10-digit Indian number starting with 6-9"
           )
	private String mobileNo;
}
