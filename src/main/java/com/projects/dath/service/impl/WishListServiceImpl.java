package com.projects.dath.service.impl;

import com.projects.dath.exception.UnknownProduct;
import com.projects.dath.model.*;
import com.projects.dath.repository.CustomerRepository;
import com.projects.dath.service.AccountService;
import com.projects.dath.service.ProductService;
import com.projects.dath.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Primary
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {
    private final CustomerRepository customerRepository;
    private final ProductService productService;
    private final AccountService accountService;
    @Override
    public List<WishList> getContent(UUID customerId) {
        var customer = customerRepository.findCustomerWishlistById(customerId);
        return customer.getWishList();
    }

    @Override
    public List<WishList> addProduct(UUID customerId, Long productId) {
        if(productService.isExist(productId)){
            var customer = accountService.getCustomer(customerId);
            return customer.saveProduct(productId);
        }
        throw new UnknownProduct("Product not found with id: " + productId, productId.toString());
    }

    @Override
    public List<WishList> discardProduct(UUID customerId, Long productId) {
        var customer = accountService.getCustomer(customerId);
        return customer.removeProduct(productId);
    }
}
