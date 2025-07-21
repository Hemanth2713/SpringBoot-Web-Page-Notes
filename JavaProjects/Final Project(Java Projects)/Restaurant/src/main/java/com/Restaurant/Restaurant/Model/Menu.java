package com.Restaurant.Restaurant.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "menu_id_seq")
    @SequenceGenerator(name = "menu_id_seq",sequenceName = "menu_id_seq",initialValue = 1001000,allocationSize = 1)
    private Long menuId;

    private String menuName; // e.g., Coffee, Pizza

    private String description;

    @Enumerated(EnumType.STRING)
    private ActiveStatus status ;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MenuItem> menuItems;
}
