package com.Restaurant.Restaurant.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDto {

	@NotBlank(message="email is required")
	private String email;
	@NotBlank(message="Password is required")
	private String password;
}
