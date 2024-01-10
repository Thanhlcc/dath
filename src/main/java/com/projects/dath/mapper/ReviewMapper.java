package com.projects.dath.mapper;

import com.projects.dath.model.Review;
import com.projects.dath.dto.ReviewDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDTO fromReview(Review review);
}
