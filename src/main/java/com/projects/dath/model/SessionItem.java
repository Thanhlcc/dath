package com.projects.dath.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class SessionItem {
    @EmbeddedId
    private SessionItemId id;

    @MapsId("productId")
    @JoinColumn(
            name = "productId",
            foreignKey = @ForeignKey(name = "fk_product_id"))
    @ManyToOne
    private Product product;

    @MapsId("sessionId")
    @JoinColumn(
            name = "sessionId",
            foreignKey = @ForeignKey(name = "fk_session_id"))
    @ManyToOne
    private ShoppingSession session;

    @Column(nullable = false)
    private Long qty = 1L;
}

@Data
@Embeddable
class SessionItemId implements Serializable {
    private Long productId;
    private Long sessionId;
}
