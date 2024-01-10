package com.projects.dath.service;

import com.projects.dath.model.Product;
import com.projects.dath.model.Review;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public interface ProductService {
    Page<Product> getProductsWithFilter(Predicate predicate, Pageable pageFormat);

    Optional<Product> getProductWith(long id);

    Product createProduct(Product newProduct);

    Iterable<Review> getProductReviews(long productId);

    void removeProductFromCatalog(Long productId);

    void storeImage(Long id, MultipartFile file) throws IOException;

    boolean isExist(Long productId);
}
