package com.projects.dath.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Assumption: only allow admin to create a category and assign it to super-category
 */
@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
public class Category {
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(columnDefinition = "text")
    private String categorySchema;

    @OneToMany(mappedBy = "superCategory")
    private Set<Category> subCategories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_category")
    private Category superCategory;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();
    public Category(String name) {
        this.name = name;
    }
}
