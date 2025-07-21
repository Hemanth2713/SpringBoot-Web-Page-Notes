package com.Restaurant.Restaurant.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="users")
public class User  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id_seq")
	@SequenceGenerator(name = "user_id_seq",sequenceName = "user_id_seq",initialValue = 10000,allocationSize = 1)
	private Long userId;
	private String name;
	private String email;
	private String password;
	private String mobileNo;
	
	@Enumerated(EnumType.STRING)
	private ActiveStatus status;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Address> addresses;

	@OneToOne(mappedBy = "user", cascade=CascadeType.ALL)
	@JsonManagedReference
	private Cart cart;

//	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
//	@JsonManagedReference
//	private List<BankAccount> bankAccounts = new ArrayList<>();
//
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Order> orders;
	
}
