package com.Restaurant.Restaurant.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateDto {

	@NotBlank(message = "Name is required")
	private String name;

//	@NotBlank(message = "Email is required")
//	@Email(message = "Invalid Email format")
//	private String email;

	@NotBlank(message = "Mobile number is required")
	@Pattern(
			regexp = "^[6-9]\\d{9}$",
			message = "Mobile number must be a valid 10-digit Indian number starting with 6-9"
	)
	private String mobileNo;
}
