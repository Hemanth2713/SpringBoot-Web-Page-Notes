package com.Restaurant.Restaurant.DTO;

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
public class AddressDto {

	private Long addressId;

	@NotBlank(message = "House Number is required")
	private String houseNumber;

	@NotBlank(message = "Street is required")
	private String street;

	@NotBlank(message = "Area is required")
	private String area;

	@NotBlank(message = "Landmark is required")
	private String landmark;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;

	@NotBlank(message = "Pincode is required")
	@Pattern(regexp = "\\d{6}", message = "Pincode must be 6 digits")
	private String pincode;


}
