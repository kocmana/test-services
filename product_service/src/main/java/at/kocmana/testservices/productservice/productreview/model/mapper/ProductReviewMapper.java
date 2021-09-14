package at.kocmana.testservices.productservice.productreview.model.mapper;

import at.kocmana.testservices.productservice.productreview.model.domain.ProductReview;
import at.kocmana.testservices.productservice.productreview.model.dto.ProductReviewCreationRequest;
import at.kocmana.testservices.productservice.productreview.model.dto.ProductReviewResponse;
import at.kocmana.testservices.productservice.productreview.model.dto.ProductReviewUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductReviewMapper {

  @Mapping(source = "product.id", target = "productId")
  ProductReviewResponse productReviewToProductReviewResponse(ProductReview productReview);

  ProductReview productReviewCreationRequestToProductReview(ProductReviewCreationRequest productReviewCreationRequest);

  ProductReview productReviewUpdateRequestToProductReview(ProductReviewUpdateRequest productReviewUpdateRequest);
}
