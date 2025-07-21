    package com.Restaurant.Restaurant.Model;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import jakarta.persistence.*;
    import lombok.*;

    @Entity
    @Table(name = "cart_item")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class CartItem {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_id_seq")
        @SequenceGenerator(
                name = "cart_item_id_seq",
                sequenceName = "cart_item_id_seq",
                initialValue = 300000,
                allocationSize = 1
        )
        private Long cartItemId;


        private Integer quantity;
        private double price;

        @ManyToOne
        @JoinColumn(name = "cartId")
        @JsonBackReference
        private Cart cart;

        @ManyToOne
        @JoinColumn(name = "item_id")
        private MenuItem menuItem;
    }
