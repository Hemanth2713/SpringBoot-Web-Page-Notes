package com.Restaurant.Restaurant.DTO;

import com.Restaurant.Restaurant.Model.ActiveStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MenuDto {

    @NotBlank(message = "Menu name is required")
    @Size(max = 100, message = "Menu name must be less than 100 characters")
    private String menuName;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

}
