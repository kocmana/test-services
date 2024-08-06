package at.kocmana.testservices.productservice.productinformation.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class ProductUpdateRequest {

  @NotNull
  @Min(1)
  Integer id;
  @NotBlank
  @Length(max = 255)
  String name;
  @NotBlank @Length(max = 500)
  String description;
  float weight;
  ProductDimensionDto dimension;
}
