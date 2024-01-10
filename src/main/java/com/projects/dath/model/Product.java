package com.projects.dath.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private BigDecimal price;

    @Column(unique = true, length = 12)
    private String sku;

    @Column(nullable = false)
    private String warranty;

    @Column(nullable = false)
    private BigDecimal qtyInStock = BigDecimal.valueOf(0);

    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode attributes;

    @JoinColumn(
            foreignKey = @ForeignKey(name = "fk_category"),
            referencedColumnName = "id",
            name = "category")
    @ManyToOne
    private Category category;

    @JoinColumn(
            foreignKey = @ForeignKey(name = "fk_supplier"),
            referencedColumnName = "id",
            name = "supplier")
    @ManyToOne
    private Supplier supplier;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "product")
    private List<WishList> subscribers;

    @ElementCollection
    @Column(name = "image")
    @JoinTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> images;

    public void addImages(List<String> images) {
        if (images != null) {
            this.images.addAll(images);
        }
    }
}
