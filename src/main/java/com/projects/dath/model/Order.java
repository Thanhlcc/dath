package com.projects.dath.model;

import com.projects.dath.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToOne
    private PaymentInfo payment;
    @OneToOne
    private Address shippingAddress;
    private Integer totalBill;
    @ManyToOne
    @JoinColumn(
            foreignKey = @ForeignKey(name = "fk_owner"),
            referencedColumnName = "id")
    private Customer owner;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<OrderItem> items= new HashSet<>();
}
