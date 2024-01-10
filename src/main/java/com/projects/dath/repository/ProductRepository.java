package com.projects.dath.repository;

import com.projects.dath.model.Product;
import com.projects.dath.model.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>
        , QuerydslPredicateExecutor<Product>, QuerydslBinderCustomizer<QProduct> {
    @Override
    default void customize(QuerydslBindings bindings, @NonNull QProduct product) {
        bindings.bind(String.class).all((StringPath path, Collection<? extends String> values) -> {
                    BooleanBuilder predicate = new BooleanBuilder();
                    values.forEach(value -> predicate.or(path.containsIgnoreCase(value)));
                    return Optional.of(predicate);
                }
        );
    }
}
