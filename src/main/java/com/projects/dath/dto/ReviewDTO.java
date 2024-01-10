package com.projects.dath.dto;

public record ReviewDTO(
        String comment,
        int rating,
        String username,
        String image
) {
}
