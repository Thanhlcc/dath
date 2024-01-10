package com.projects.dath.mapper;

import com.projects.dath.model.Product;
import com.projects.dath.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(
            target = "category",
            expression = "java(product.getCategory().getName())")
    @Mapping(
            target = "supplier",
            expression = "java(product.getSupplier().getName())")
    ProductDTO fromProduct(Product product);

    @Mapping(
            target = "category",
            expression = "java(new com.projects.dath.model.Category(productDTO.category()))")
    @Mapping(
            target = "supplier",
            expression = "java(new com.projects.dath.model.Supplier(productDTO.supplier()))")
    Product toProduct(ProductDTO productDTO);

//    @Mapping(
//            target = "category",
//            expression = "java(new com.projects.dath.domain.Category(newProduct.category()))")
//    @Mapping(
//            target = "supplier",
//            expression = "java(new com.projects.dath.domain.Supplier(newProduct.supplier()))")
//    Product toProduct(ProductUpload newProduct);
}
