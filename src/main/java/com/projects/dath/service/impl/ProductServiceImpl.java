package com.projects.dath.service.impl;

import com.projects.dath.model.Product;
import com.projects.dath.model.Review;
import com.projects.dath.exception.UnknownCategory;
import com.projects.dath.exception.UnknownProduct;
import com.projects.dath.exception.UnknownSupplier;
import com.projects.dath.repository.CategoryRepository;
import com.projects.dath.repository.ProductRepository;
import com.projects.dath.repository.ReviewRepository;
import com.projects.dath.repository.SupplierRepository;
import com.projects.dath.service.FileService;
import com.projects.dath.service.ProductService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Component
@Primary
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;
    private final FileService imageStorageService;

    @Override
    public Page<Product> getProductsWithFilter(Predicate predicate, Pageable pageFormat) {
        return productRepository.findAll(predicate, pageFormat);
    }

    @Override
    public Optional<Product> getProductWith(long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product createProduct(Product newProduct) {
        // Validate product information
        if (!supplierRepository.existsById(newProduct.getSupplier().getId())) {
            throw new UnknownSupplier("Undefined supplier name=" + newProduct.getSupplier().getName(), newProduct.getName());
        }
        if (!categoryRepository.existsById(newProduct.getCategory().getId())) {
            throw new UnknownCategory("Undefined category " + newProduct.getCategory().getName(), Map.of("name", newProduct.getName()));
        }
        return productRepository.save(newProduct);
    }

    @Override
    public Iterable<Review> getProductReviews(long productId) {
        return reviewRepository.findTop10ByProductId(productId);
    }

    @Override
    @Transactional
    public void removeProductFromCatalog(Long productId) {
       if(isExist(productId)){
              throw new UnknownProduct("Product not found with id= " + productId, productId.toString());
       }
        // TODO: implement exception handling
        imageStorageService.removeAll();
        productRepository.deleteById(productId);
    }

    @Override
    public void storeImage(Long productId, MultipartFile file) throws IOException {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new UnknownProduct("Product not found with id= " + productId, productId.toString()));
        var key = imageStorageService.store(file);
        product.addImages(List.of(key));
        productRepository.save(product);
    }

    @Override
    public boolean isExist(Long productId) {
        return productRepository.existsById(productId);
    }
}
