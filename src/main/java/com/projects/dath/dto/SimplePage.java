package com.projects.dath.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SimplePage<T>(
        @NotNull
        Iterable<T> data,
        @JsonProperty("meta-data")
        MetaData metaData
) {
    public SimplePage(
            Iterable<T> records,
            int page,
            int size,
            int maxPage
    ) {
        this(records, new MetaData(page, size, maxPage));
    }
}
record MetaData(
        @Min(0) int page,
        @Min(0) int size,
        @Min(0) int maxPage
) {
}