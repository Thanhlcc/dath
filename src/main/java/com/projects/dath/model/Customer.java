package com.projects.dath.model;

import com.projects.dath.exception.UnknownProduct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer extends User{
    public Customer() {
        super();
        this.setRole(UserRole.CUSTOMER);
    }
    @OneToOne(
            mappedBy = "owner",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private ShoppingSession shoppingSession;

    @ManyToMany(mappedBy = "owners")
    private Set<Address> addresses = new HashSet<>();

    private boolean isActive = true;

    @OneToMany(mappedBy = "owner")
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<WishList> wishList;

    public List<WishList> saveProduct(Long productId) {
        var newWishListId = new WishListId(this.getId(), productId);
        this.wishList.add(new WishList(newWishListId));
        return this.wishList;
    }

    public List<WishList> removeProduct(Long productId) {
        if (this.wishList.removeIf(product -> product.getId().getProductId().equals(productId))) {
            return this.wishList;
        }
        else throw new UnknownProduct("Product not found with id: " + productId, productId.toString());
    }

}
