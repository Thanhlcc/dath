package com.projects.dath.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @EmbeddedId
    private ReviewId id;

    @MapsId("customerId")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Check(constraints = "rating between 0 and 5", name = "rating_constraint")
    private int rating;

    @Column(columnDefinition = "text")
    private String comment;
    @UpdateTimestamp
    private LocalDateTime lastModified;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ElementCollection
    @CollectionTable(
            name = "review_images",
            joinColumns = {
                    @JoinColumn(name = "item", referencedColumnName = "product_id"),
                    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
            })
    @Column(name="image")
    private List<String> images = new ArrayList<>();
}

@Embeddable
@Data
class ReviewId implements Serializable {
    @Column(name = "customer_id")
    private UUID customerId;
    @Column(name = "product_id")
    private Long productId;
}
