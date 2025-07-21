package com.Restaurant.Restaurant.Model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private int id;
    private String name;
    private String email;
    private String mobileNo;
    private ActiveStatus status;
    private Role role;
    private Integer cartId; 
}
 
