package com.projects.dath.controller;

import com.projects.dath.dto.ProductDTO;
import com.projects.dath.dto.SimplePage;
import com.projects.dath.exception.UnknownProduct;
import com.projects.dath.mapper.ProductMapper;
import com.projects.dath.model.Product;
import com.projects.dath.model.QProduct;
import com.projects.dath.service.ProductService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    public final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    SimplePage<ProductDTO> viewProductCatalog(
            @QuerydslPredicate(root = Product.class) Predicate predicate,
            @RequestParam(required = false, value = "max-price") Optional<BigDecimal> maxPrice,
            @RequestParam(required = false, value = "min-price") Optional<BigDecimal> minPrice,
            @PageableDefault(page = 0, sort = "name") Pageable pageable
    ) {
        // Prepare the query string
        QProduct $ = QProduct.product;
        BooleanBuilder builder = new BooleanBuilder().and(predicate);
        maxPrice.ifPresent((_maxPrice) -> builder.and($.price.lt(_maxPrice)));
        minPrice.ifPresent((_minPrice) -> builder.and($.price.goe(_minPrice)));
        // Query the requested data
        Page<Product> resultPage = productService.getProductsWithFilter(builder, pageable);

        return new SimplePage<>(
                resultPage.map(productMapper::fromProduct).getContent(),
                pageable.getPageNumber(),
                resultPage.getSize(),
                resultPage.getTotalPages()
        );
    }

    @GetMapping("/{id}")
    ProductDTO viewProductDetail(@PathVariable("id") Long id) {
        var product = productService.getProductWith(id)
                .orElseThrow(() -> new UnknownProduct("Product not found with id=" + id, id.toString()));
        return productMapper.fromProduct(product);
    }

    @PostMapping
    ProductDTO createProduct(
            @RequestBody @Valid ProductDTO newProduct
    ) {
        Product product = productMapper.toProduct(newProduct);
        var savedProduct = productService.createProduct(product);
        return productMapper.fromProduct(savedProduct);
    }

    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(
            @RequestParam("fileImage") MultipartFile fileImage,
            @PathVariable("id") Long id
    ) throws IOException {
        productService.storeImage(id, fileImage);
        return "Success";
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> removeProduct(@PathVariable Long id) {
        productService.removeProductFromCatalog(id);
        return ResponseEntity.ok().body("Deleted product with id" + id);
    }
}

