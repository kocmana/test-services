package at.kocmana.testservices.productservice.productreview.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class ProductReviewCreationRequest {

  @NotNull
  int customerId;
  @Min(value = 0) @Max(value = 5)
  int stars;
  @Length(max = 500)
  String review;
}
