package com.Restaurant.Restaurant.DTO;



import com.Restaurant.Restaurant.Model.ActiveStatus;
import com.Restaurant.Restaurant.Model.Role;
import com.Restaurant.Restaurant.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String mobileNo;
    private ActiveStatus status;
    private Role role;

    public UserResponseDto(User user) {
    }
//    private Integer cartId;
}
 
