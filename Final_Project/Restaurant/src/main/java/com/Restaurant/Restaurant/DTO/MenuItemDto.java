package com.Restaurant.Restaurant.DTO;

import com.Restaurant.Restaurant.Model.AvailabilityStatus;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuItemDto {

    @NotBlank(message = "Item name is required")
    private String itemName;
    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;
//    @NotNull(message = "isVeg field is required")
//    private Boolean isVeg;
    @NotBlank(message = "Image URL is required")
    private String imageUrl;
//    @NotNull(message = "Preparation time is required")
//    @Min(value = 1, message = "Preparation time must be at least 1 minute")
//    private Integer preparationTime;
    @NotNull(message = "Menu ID is required")
    private Long menuId;
}
