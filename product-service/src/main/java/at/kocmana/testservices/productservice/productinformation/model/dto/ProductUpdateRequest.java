package at.kocmana.testservices.productservice.productinformation.model.dto;

import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class ProductUpdateRequest {

  @NotNull @Min(1)
  Integer id;
  @NotBlank @Length(max = 255)
  String name;
  @NotBlank @Length(max = 500)
  String description;
  float weight;
  ProductDimensionDto dimension;
}
