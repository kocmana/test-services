package at.kocmana.testservices.productservice.productinformation.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDimensionDto {
  private float width;
  private float height;
  private float depth;
}
