package com.projects.dath.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.util.List;

public record ProductDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        Integer qtyInStock,
        String description,
        String name,
        BigDecimal price,
        String sku,
        String warranty,
        String category,
        String supplier,
        JsonNode attributes,
        List<String> images
) {
}
