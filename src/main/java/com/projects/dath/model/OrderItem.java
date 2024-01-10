package com.projects.dath.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @MapsId("orderId")
    @JoinColumn(
        name = "order_id",
        foreignKey = @ForeignKey(name = "fk_order_item_order")
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @MapsId("productId")
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "fk_order_item_product")
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Integer qty = 1;

    @CreationTimestamp
    private LocalDateTime addedTime;
}
@Embeddable
@Data
class OrderItemId implements Serializable {
    private Long orderId;
    private Long productId;
}
