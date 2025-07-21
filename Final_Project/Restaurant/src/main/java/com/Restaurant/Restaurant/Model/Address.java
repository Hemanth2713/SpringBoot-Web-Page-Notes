package com.Restaurant.Restaurant.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_id_seq")
	@SequenceGenerator(name = "address_id_seq",sequenceName = "address_id_seq",initialValue = 100100,allocationSize = 1)
	private Long addressId;

	private String houseNumber;         // e.g., "12A"
	private String street;              // e.g., "MG Road"
	private String area;                // e.g., "Hi-Tech City"
	private String landmark;            // e.g., "Near Cyber Towers"
	private String city;                // e.g., "Hyderabad"
	private String state;               // e.g., "Telangana"
	private String pincode;             // e.g., "500081"
	private String country = "India";   // Defaulted for Indian addresses

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
}
