package com.projects.dath.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record ProductUpload(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        @Min(0) Integer qtyInStock,
        String description,
        @NotNull @NotBlank String name,
        @Min(0) @NotNull BigDecimal price,
        @NotBlank String sku,
        String warranty,
        String category,
        String supplier,
        @NotNull @NotBlank JsonNode attributes,
        List<MultipartFile> images
){
}
