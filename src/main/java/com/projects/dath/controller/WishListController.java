package com.projects.dath.controller;

import com.projects.dath.dto.WishListAddReq;
import com.projects.dath.model.Customer;
import com.projects.dath.model.WishList;
import com.projects.dath.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;

    @GetMapping("/wishlist")
    List<WishList> findMyWishList(
            @AuthenticationPrincipal Customer customer
//            @QuerydslPredicate(root = WishList.class) Predicate predicate,
//            @PageableDefault(page = 0, sort = "addedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return wishListService.getContent(customer.getId());
    }

    @PostMapping("/add-item")
    List<WishList> addToWishlist(
            @AuthenticationPrincipal Customer customer,
            WishListAddReq req
    ) {
        return wishListService.addProduct(customer.getId(), req.productId());
    }

    @DeleteMapping("/remove-item")
    List<WishList> discardItem(
            @AuthenticationPrincipal Customer customer,
            WishListAddReq req
    ) {
        return wishListService.discardProduct(customer.getId(), req.productId());
    }
}