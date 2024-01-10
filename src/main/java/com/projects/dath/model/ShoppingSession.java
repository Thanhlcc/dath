package com.projects.dath.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Slf4j
public class ShoppingSession extends AbstractAggregateRoot<ShoppingSession> {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(
            foreignKey = @ForeignKey(name = "fk_owner"),
            referencedColumnName = "id"
    )
    private Customer owner;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    private BigDecimal totalCost = BigDecimal.valueOf(0);

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private Set<SessionItem> sessionItems = new HashSet<>();

    public void addItem(SessionItem item) {
        sessionItems.add(item);
        log.info("Add item {}", item.getId());
    }

    public void discardItem(SessionItem item) {
        sessionItems.remove(item);
        log.info("Remove item {}", item.getId());
    }
}

