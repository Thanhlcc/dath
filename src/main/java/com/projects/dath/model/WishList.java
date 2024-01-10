package com.projects.dath.model;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@QueryEntity
public class WishList {
    @EmbeddedId
    private WishListId id;
    @MapsId("customerId")
    @JoinColumn(
            name = "customerId",
            foreignKey = @ForeignKey(name = "wishlist_customer_fk")
    )
    @ManyToOne
    private Customer customer;

    @MapsId("productId")
    @JoinColumn(
            name = "productId",
            foreignKey = @ForeignKey(name = "wishlist_product_fk")
    )
    @ManyToOne
    private Product product;

    @CreationTimestamp
    private LocalDateTime addedAt;
    public WishList( WishListId id) {
        this.id = id;
    }
}

