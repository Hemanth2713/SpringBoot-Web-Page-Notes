package com.Restaurant.Restaurant.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "menu_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "menuItem_id_seq")
    @SequenceGenerator(name = "menuItem_id_seq",sequenceName = "menuItem_id_seq",initialValue = 110000,allocationSize = 1)
    private Long itemId;
    private String itemName;
    private String description;
    private Double price;
    private AvailabilityStatus availability;
    // private Boolean isVeg; // true = vegetarian
    private String imageUrl;
    //private Integer preparationTime; // in minutes
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
}
