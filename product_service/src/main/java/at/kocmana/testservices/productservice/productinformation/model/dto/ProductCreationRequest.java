package at.kocmana.testservices.productservice.productinformation.model.dto;

import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Value
public class ProductCreationRequest {

  @NotBlank @Length(max = 255)
  String name;
  @NotBlank @Length(max = 500)
  String description;
  float weight;
  ProductDimensionDto dimension;
}
