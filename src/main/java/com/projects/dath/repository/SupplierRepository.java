package com.projects.dath.repository;

import com.projects.dath.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Boolean existsByName(String name);
}
